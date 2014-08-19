package agroludos.business.as.gestoremds;

import agroludos.exceptions.DBFactoryException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.DBFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.system.SystemConf;
import agroludos.to.ManagerDiSistemaTO;

class ASGestoreManagerDiSistema implements LManagerDiSistema, SManagerDiSistema{
	private SystemConf sysConf;
	private DBFactory dbFact;	
	
	ASGestoreManagerDiSistema(DBFactory dbFact, SystemConf sysConf){
		this.dbFact = dbFact;
		this.sysConf = sysConf;
	}

	@Override
	public boolean inserisciManagerDiSistema(ManagerDiSistemaTO mdsto) {
		boolean res = false;
		ManagerDiSistemaDAO daoMan = getManagerDiSistemaDAO();
		res = daoMan.crea(mdsto);
		return res;
	}
	
	private ManagerDiSistemaDAO getManagerDiSistemaDAO(){
		DBDAOFactory dbDAOFact = null;
		
		try {
			dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		} catch (DBFactoryException e) {
			e.printStackTrace();
		}
		
		return dbDAOFact.getManagerDiSistemaDAO();
	}
}