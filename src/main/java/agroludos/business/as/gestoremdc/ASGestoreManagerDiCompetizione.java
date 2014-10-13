package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.db.StatoUtenteDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoUtenteTO;

class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiCompetizioneTO inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException {

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.create(mdcto);

	}

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.getByUsername(mdcto.getUsername());
	}

	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO(); 
		return daoMan.getAll();
	}

	@Override
	public ManagerDiCompetizioneTO modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		ManagerDiCompetizioneTO res = (ManagerDiCompetizioneTO)daoMan.update(mdcto);
		return res;
	}

	@Override
	public ManagerDiCompetizioneTO eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		StatoUtenteDAO suDAO = dbDAOFact.getStatoUtenteDAO();
		List<StatoUtenteTO> stati = suDAO.getAll();
		mdcto.setStatoUtente(stati.get(0));
		return daoMan.update(mdcto);
	}
}