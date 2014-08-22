package agroludos.business.as.gestoremds;

import agroludos.exceptions.DBFactoryException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.DBFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.system.SystemConf;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

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
		ManagerDiSistemaDAO daoMan = null;
		
		try {
			daoMan = getManagerDiSistemaDAO();
		} catch (DBFactoryException e) {
			e.printStackTrace();
		}
		
		res = daoMan.crea(mdsto);
		
		return res;
	}

	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DBFactoryException{
		DBDAOFactory dbDAOFact = null;

		dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());

		return dbDAOFact.getManagerDiSistemaDAO();
	}

	@Override
	public ManagerDiSistemaTO getManagerDiSistema(UtenteTO uto) {
		ManagerDiSistemaDAO daoMan = null;
		
		try {
			daoMan = getManagerDiSistemaDAO();
		} catch (DBFactoryException e) {
			e.printStackTrace();
		}
		
		return daoMan.read(uto);
	}
}