package agroludos.business.as.gestoreutente;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.UtenteDAO;
import agroludos.to.EmailTO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;

class ASGestoreUtente extends AgroludosAS implements LUtente, SUtente{
	PasswordEncryption pwdEnc;

	ASGestoreUtente(PasswordEncryption pwdEnc){
		this.pwdEnc = pwdEnc;
	}

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

		if(res.getId() == -1)
			throw new UserNotFoundException();

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

		if(daoUtente.esisteUsername(uTO))
			uTO = daoUtente.getByUsername(uTO.getUsername());
		else if(daoUtente.esisteEmail(uTO))
			uTO = daoUtente.getByEmail(uTO.getEmail());
		else
			throw new UserNotFoundException("Utente inesistente!");

		return uTO;
	}


	@Override
	public UtenteTO passwordDimenticata(UtenteTO uTO)
			throws DatabaseException, UserNotFoundException, IOException {

		UtenteDAO<UtenteTO> daoUtente = this.getUtenteDAO();

		//se non esiste viene sollevata un'eccezione UserNotFoundException
		uTO = checkUtente(uTO);

		String psw = RandomStringUtils.randomAlphanumeric(8);

		uTO.setPassword(this.pwdEnc.encryptPassword(psw));
		daoUtente.update(uTO);

		//TODO PasswordDimenticata
		EmailTO mail = toFact.createEmailTO();
		mail.setOggetto("Password dimenticata");
		mail.setMessage("I dati di accesso sono : \n"
				+ "Username: " + uTO.getUsername() + "\n"
				+ "Nuova Password: " + psw);

		mail.addDestinatario(uTO);

		return uTO;
	}
}