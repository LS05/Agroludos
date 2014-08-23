package agroludos.business.as.gestoremdc;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DBFactoryException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class ASGestoreManagerDiCompetizione extends AgroludosAS implements LManagerDiCompetizione, SManagerDiCompetizione{

	@Override
	public boolean inserisciManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) {
		boolean res = false;
		ManagerDiCompetizioneDAO daoMan = null;
		
		try {
			daoMan = getManagerDiCompetizioneDAO();
		} catch (DBFactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		res = daoMan.crea(mdcto);
		return res;
	}

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(ManagerDiCompetizioneTO mdcto) {
		ManagerDiCompetizioneDAO daoMan = null;
		
		try {
			daoMan = getManagerDiCompetizioneDAO();
		} catch (DBFactoryException e) {
			e.printStackTrace();
		}
		
		return daoMan.read(mdcto);
	}
	
	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DBFactoryException{
		DBDAOFactory dbDAOFact = null;

		dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());

		return dbDAOFact.getManagerDiCompetizioneDAO();
	}
}