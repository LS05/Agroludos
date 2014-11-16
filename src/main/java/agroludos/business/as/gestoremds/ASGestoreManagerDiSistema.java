package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.MdsNotFoundException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;
import agroludos.utility.PasswordEncryption;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Manager Di Sistema.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link ManagerDiSistemaDAO} 
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore Manager Di Sistema espone la logica di business riguardante il Manager di Sistema 
 * tramite due interfacce. L'interfaccia {@link LManagerDiSistema} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SManagerDiSistema} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreManagerDiSistema extends AgroludosAS implements LManagerDiSistema, SManagerDiSistema{
	private PasswordEncryption pwdEnc;
	private AgroludosValidator validator;

	/**
	 * Il costrute inizializza gli attributi validator e pwdEnc
	 * 
	 * @param pwdEnc
	 * @param validator
	 * @see agroludos.business.validator.AgroludosValidator
	 * @see agroludos.utility.PasswordEncryption
	 */	
	ASGestoreManagerDiSistema(PasswordEncryption pwdEnc, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
	}

	/**
	 * Il metodo restituisce un'istanza di {@link ManagerDiSistemaDAO}
	 * 
	 * @return {@link ManagerDiSistemaDAO}
	 * @throws DatabaseException
	 * @see {@link agroludos.integration.dao.db.ManagerDiSistemaDAO}
	 */
	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiSistemaDAO();
	}

	/**
	 * Il metodo restituisce un'istanza di {@link TipoUtenteDAO}
	 * 
	 * @return {@link TipoUtenteDAO}
	 * @throws DatabaseException
	 */
	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoUtenteDAO();
	}

	/**
	 * Il metodo restituisce un'istanza di {@link StatoUtenteDAO}
	 * 
	 * @return {@link StatoUtenteDAO}
	 * @throws DatabaseException
	 */
	private StatoUtenteDAO getStatoUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getStatoUtenteDAO();
	}

	/**
	 * Il metodo inserisce un nuovo Manager Di Sistema controllando che non esistano email e username 
	 * presi in input e effettuando la validazione dei campi.
	 * Se esistono username o email solleva {@link UtenteEsistenteException}
	 * Utilizza i DAO {@link TipoUtenteDAO} {@link StatoUtenteDAO} {@link ManagerDiSistemaDAO}
	 */
	@Override
	public ManagerDiSistemaTO nuovoManagerDiSistema(ManagerDiSistemaTO mdsTO) 
			throws DatabaseException, ValidationException {

		ManagerDiSistemaDAO mdsDao = this.getManagerDiSistemaDAO();

		if( !mdsDao.esisteEmail(mdsTO) && !mdsDao.esisteUsername(mdsTO) ){ 

			this.validator.validate(mdsTO);

			String inputPassword = mdsTO.getPassword();
			mdsTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

			TipoUtenteDAO tipoUtenteDao = this.getTipoUtenteDAO();
			StatoUtenteDAO statoUtenteDao = this.getStatoUtenteDAO();

			mdsTO.setTipoUtente(tipoUtenteDao.getTipoUtenteMds());
			mdsTO.setStatoUtente(statoUtenteDao.getStatoAttivo());
		} else{
			if(mdsDao.esisteEmail(mdsTO)){
				throw new UtenteEsistenteException("Email già esistente!");
			}else{
				throw new UtenteEsistenteException("Username già esistente");
			}
		}

		return this.getManagerDiSistemaDAO().create(mdsTO);
	}

	/**
	 * Il metodo restituisce un Manager di Sistema tramite l'username passato in input, 
	 * utilizza il DAO {@link ManagerDiSistemaDAO}
	 */
	@Override
	public ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException {
		UtenteTO uTO = this.getManagerDiSistemaDAO().getByUsername(mdsto.getUsername());
		return (ManagerDiSistemaTO) uTO;
	}

	/**
	 * Il metodo controlla che all'interno della sorgente dati esista o meno un Manager di Sistema.
	 * Se non esiste solleva l'eccezione {@link MdsNotFoundException} altrimenti ritorna true
	 */
	@Override
	public boolean checkMds() throws DatabaseException, MdsNotFoundException {
		boolean res = false;

		if(this.getManagerDiSistemaDAO().checkMds()){
			res = true;
		} else {
			throw new MdsNotFoundException();
		}

		return res;
	}
}