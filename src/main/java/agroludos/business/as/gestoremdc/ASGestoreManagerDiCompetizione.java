package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.business.validator.AgroludosValidator;
import agroludos.exceptions.business.MdcCmpAttiveException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.TipoUtenteTO;
import agroludos.utility.PasswordEncryption;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore Manager Di Competizione.</b><br /> 
 * L'obiettivo della classe &egrave; quello di centralizzare ed incapsulare il funzionamento
 * dei servizi andando a ridurre l'accoppiamento con le altre componenti del sistema.
 * Il gestore utilizza una serie di {@link AgroludosTO} (Transfer Object o Data Transfer Object
 * la cui tipologia dipende dal servizio) e sfrutta il {@link ManagerDiCompetizioneDAO}
 * (Data Access Object) per occuparsi della persistenza di tali oggetti.</br>
 * Il Gestore Manager Di Competizione espone la logica di business riguardante i Manager Di Competizione 
 * tramite due interfacce. L'interfaccia {@link LManagerDiCompetizione} fornisce i servizi 
 * di lettura, mentre l'interfaccia {@link SManagerDiCompetizione} offre i servizi di scrittura.</br>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

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
	ASGestoreManagerDiCompetizione(PasswordEncryption pwdEnc, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
	}

	/**
	 * Il metodo restituisce un'istanza di {@link ManagerDiCompetizioneDAO}
	 * 
	 * @return {@link ManagerDiCompetizioneDAO}
	 * @throws DatabaseException
	 * @see {@link agroludos.integration.dao.db.ManagerDiCompetizioneDAO}
	 */
	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiCompetizioneDAO();
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
	 * Il metodo inserisce un Manager di Competizione nella sorgente dati attraverso il DAO {@link ManagerDiCompetizioneDAO}
	 * Prima di inserire il Manager effettua la validazione dei campi, controlla se esiste già l'email o
	 * l'username inseriti sollevando {@link UtenteEsistenteException}
	 */
	@Override
	public ManagerDiCompetizioneTO inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcTO) 
			throws DatabaseException, ValidationException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();

		this.validator.validate(mdcTO);

		if( daoMan.esisteEmail(mdcTO)){
			throw new UtenteEsistenteException("Email già esistente");
		}

		if( daoMan.esisteUsername(mdcTO) ){
			throw new UtenteEsistenteException("Username già esistente");
		}

		String inputPassword = mdcTO.getPassword();
		mdcTO.setPassword(this.pwdEnc.encryptPassword(inputPassword));

		TipoUtenteTO tipoUtente = this.getTipoUtenteDAO().getTipoUtenteMdc();
		mdcTO.setTipoUtente(tipoUtente);

		return daoMan.create(mdcTO);
	}

	/**
	 * Il metodo restituisce un Manager di Competizione tramite l'username
	 */
	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) 
			throws DatabaseException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.getByUsername(mdcto.getUsername());

	}

	/**
	 * Il metodo restituisce una lista con tutti i Manager di Competizioni presenti nella sorgente dati,
	 * utilizza il DAO {@link ManagerDiCompetizioneDAO}
	 */
	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() 
			throws DatabaseException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO(); 
		return daoMan.getAllManagerDiCompetizione();

	}

	/**
	 * Il metodo, dopo aver validato i campi, modifica il manager di competizione tramite il DAO
	 * {@link ManagerDiCompetizioneDAO}
	 */
	@Override
	public ManagerDiCompetizioneTO modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException, ValidationException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();

		this.validator.validate(mdcto);
		ManagerDiCompetizioneTO res = (ManagerDiCompetizioneTO)daoMan.update(mdcto);

		return res;
	}

	/**
	 * Il metodo, dopo aver controllato che il Manager in input non gestica competizioni attive tramite
	 * {@link #checkMdcCmpAttive(ManagerDiCompetizioneTO)}, elimina il Manager di Competizione in 
	 * input tramite il DAO {@link ManagerDiCompetizioneDAO}
	 */
	@Override
	public ManagerDiCompetizioneTO eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException, ValidationException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());

		checkMdcCmpAttive(mdcto);
		
		StatoUtenteDAO suDAO = dbDAOFact.getStatoUtenteDAO();
		List<StatoUtenteTO> stati = suDAO.getAll();

		mdcto.setStatoUtente(stati.get(0));

		return daoMan.update(mdcto);
	}

	/**
	 * Il metodo controlla che il Manager di Competizione in input non gestica competizioni attive cioè 
	 * competizioni che abbiano lo stato "aperta alle iscrizioni", "chiusa alle iscrizioni" o "in corso"
	 */
	@Override
	public ManagerDiCompetizioneTO checkMdcCmpAttive(ManagerDiCompetizioneTO mdcTO) 
			throws DatabaseException, MdcCmpAttiveException{

		CompetizioneDAO daoCmp = this.dbFact.getDAOFactory(this.sysConf.getTipoDB()).getCompetizioneDAO();

		for(CompetizioneTO cmpTO: daoCmp.readCompetizioniAttive()){
			if(cmpTO.getManagerDiCompetizione().getId() == mdcTO.getId()){
				throw new MdcCmpAttiveException();
			}
		}

		return mdcTO;
	}
}