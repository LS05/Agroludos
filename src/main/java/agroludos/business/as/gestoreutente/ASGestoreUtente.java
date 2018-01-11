package agroludos.business.as.gestoreutente;

import java.io.IOException;
import java.text.MessageFormat;

import org.apache.commons.lang.RandomStringUtils;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.UserNotFoundException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;
import agroludos.utility.email.AgroludosMail;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Utente.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link UtenteDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore Utente espone la logica di business riguardante gli utentei 
 * tramite due interfacce. L'interfaccia {@link LUtente} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SUtente} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{
	PasswordEncryption pwdEnc;
	private AgroludosValidator validator;
	private AgroludosMail agroludosMail;

	/**
	 * Il costruttore inizilizza le variabili pwdEnc, validator e agroludosMail
	 * @param pwdEnc
	 * @param validator
	 * @param agroludosMail
	 */
	ASGestoreUtente(PasswordEncryption pwdEnc, AgroludosValidator validator, AgroludosMail agroludosMail){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
		this.agroludosMail = agroludosMail;
	}

	/**
	 * 
	 * @return un'istanza di {@link UtenteDAO}
	 * @throws DatabaseException
	 */
	private UtenteDAO<UtenteTO> getUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getUtenteDAO();
	}


	@Override
	public UtenteTO autenticazioneUtente(UtenteTO uTO) throws DatabaseException, UserNotFoundException {
		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();

		String inputPassword = uTO.getPassword();
		uTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

		UtenteTO res = daoUtente.getUtente(uTO);

		if(res.getId() == -1){
			throw new UserNotFoundException();
		}

		return res;
	}

	@Override
	public UtenteTO getUtenteByUsername(UtenteTO uTO)
			throws DatabaseException, UserNotFoundException, IOException {

		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();
		checkUtente(uTO);
		UtenteTO user = daoUtente.getByUsername(uTO.getUsername());

		return user;
	}

	@Override
	public UtenteTO checkUtente(UtenteTO uTO)
			throws DatabaseException, UserNotFoundException, IOException {

		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();

		if(daoUtente.esisteUsername(uTO) && daoUtente.esisteEmail(uTO)){
			uTO = daoUtente.getByUsername(uTO.getUsername());
		}else{
			throw new UserNotFoundException("Utente inesistente!");
		}

		return uTO;
	}


	/**
	 * Il metodo dopo aver aggiornato i dati di accesso invia una email all'utente interessato
	 * con i nuovi dati di accesso
	 * @see EmailTO
	 * @see AgroludosMail
	 */
	@Override
	public UtenteTO passwordDimenticata(UtenteTO uTO)
			throws DatabaseException, UserNotFoundException, IOException {

		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();

		if(daoUtente.esisteEmail(uTO)){
			uTO = daoUtente.getByEmail(uTO.getEmail());
		}else{
			throw new UserNotFoundException("Email non presente nel sistema!");
		}

		String psw = RandomStringUtils.randomAlphanumeric(8);

		uTO.setPassword(this.pwdEnc.encryptPassword(psw));
		daoUtente.update(uTO);

		EmailTO mail = toFact.createEmailTO();

		String mailSubj = this.sysConf.getString("mailPwdDimSubj");
		String mailMsg = this.sysConf.getString("mailPwdDimMsg");

		mail.setOggetto(mailSubj);
		String realMsg = MessageFormat.format(mailMsg, uTO.getUsername(), psw);
		mail.setMessage(realMsg);

		mail.addDestinatario(uTO);

		this.agroludosMail.sendEmail(mail);

		return uTO;
	}

	/**
	 * Il metodo dopo aver validato i nuovi dati di accesso controlla se esiste già l'email inserita
	 * sollevando l'eccezione {@link UtenteEsistenteException} se risulta vero, effettua la modifica
	 * tramite {@link UtenteDAO}
	 */
	@Override
	public UtenteTO modificaDatiAccesso(UtenteTO uTO) throws DatabaseException, ValidationException {
		UtenteDAO<UtenteTO> daoUto = this.getUtenteDAO();

		this.validator.validate(uTO);

		UtenteTO originUto = daoUto.getByID(uTO.getId());

		//se l'utente vuole modificare l'email controllo che non esista già una
		if(originUto.getEmail().compareTo(uTO.getEmail()) != 0){
			if(daoUto.esisteEmail(uTO)){
				throw new UtenteEsistenteException("Hai inserito la stessa Email");
			}
		}

		//se l'utente vuole modificare la password allora effettuo l'encript di quella inserita
		if(originUto.getPassword().compareTo(uTO.getPassword()) != 0){
			String inputPassword = uTO.getPassword();
			uTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));
		}

		return daoUto.update(uTO);
	}
}