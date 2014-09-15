package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiCompetizioneDAO();
	}

	@Override
	public boolean inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException {
		boolean res = false;

		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		res = daoMan.crea(mdcto);

		return res;
	}

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) throws DatabaseException {
		ManagerDiCompetizioneTO manTO = null;
		
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		manTO = daoMan.getByUsername(mdcto.getUsername());
		
		return manTO;
	}

	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerCompetizione() throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.readAll();
	}

	@Override
	public boolean modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.update(mdcto);
	}

	@Override
	public boolean eliminaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.update(mdcto);
	}
}