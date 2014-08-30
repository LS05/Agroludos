package agroludos.business.as.gestoremdc;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

	@Override
	public boolean inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) {
		boolean res = false;
		
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		res = daoMan.crea(mdcto);
		
		return res;
	}

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) {
		return getManagerDiCompetizioneDAO().read(mdcto);
	}

	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerCompetizione() {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.readAll();
	}
	
	@Override
	public boolean modificaManagerDiCompetizione(ManagerDiCompetizioneTO mdcto)
			throws DatabaseException {
		ManagerDiCompetizioneDAO daoMan = getManagerDiCompetizioneDAO();
		return daoMan.update(mdcto);
	}
	
	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO(){
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiCompetizioneDAO();
	}
}