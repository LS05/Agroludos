package agroludos.business.as.gestoremdc;

import agroludos.exceptions.DBFactoryException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.DBFactory;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.system.SystemConf;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.UtenteTO;

class ASGestoreManagerDiCompetizione implements LManagerDiCompetizione, SManagerDiCompetizione{
	private SystemConf sysConf;
	private DBFactory dbFact;	

	ASGestoreManagerDiCompetizione(DBFactory dbFact, SystemConf sysConf){
		this.dbFact = dbFact;
		this.sysConf = sysConf;
	}

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

	private ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() throws DBFactoryException{
		DBDAOFactory dbDAOFact = null;

		dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());

		return dbDAOFact.getManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiCompetizioneTO getManagerDiCompetizione(UtenteTO uto) {
		ManagerDiCompetizioneDAO daoMan = null;
		
		try {
			daoMan = getManagerDiCompetizioneDAO();
		} catch (DBFactoryException e) {
			e.printStackTrace();
		}
		
		return daoMan.read(uto);
	}
}