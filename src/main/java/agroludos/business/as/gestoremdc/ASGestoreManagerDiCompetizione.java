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

class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

	private PasswordEncryption pwdEnc;
	private AgroludosValidator validator;

	ASGestoreManagerDiCompetizione(PasswordEncryption pwdEnc, AgroludosValidator validator){
		this.pwdEnc = pwdEnc;
		this.validator = validator;
	}

	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiCompetizioneDAO();
	}

	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoUtenteDAO();
	}

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

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) 
			throws DatabaseException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.getByUsername(mdcto.getUsername());

	}

	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() 
			throws DatabaseException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO(); 
		return daoMan.getAllManagerDiCompetizione();

	}

	@Override
	public ManagerDiCompetizioneTO modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException, ValidationException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();

		this.validator.validate(mdcto);
		ManagerDiCompetizioneTO res = (ManagerDiCompetizioneTO)daoMan.update(mdcto);

		return res;
	}

	@Override
	public ManagerDiCompetizioneTO eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException, ValidationException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());

		this.validator.validate(mdcto);

		StatoUtenteDAO suDAO = dbDAOFact.getStatoUtenteDAO();
		List<StatoUtenteTO> stati = suDAO.getAll();

		mdcto.setStatoUtente(stati.get(0));

		return daoMan.update(mdcto);
	}

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