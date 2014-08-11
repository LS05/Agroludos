package agroludos.business.as.gestoremds;

import agroludos.integration.dao.db.DBFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.UtenteTO;

class ASGestoreManagerDiSistema implements LManagerDiSistema, SManagerDiSistema{
	private DBFactory daoFact;
	private ManagerDiSistemaDAO daoMan;
	
	ASGestoreManagerDiSistema(){
//		this.daoFact = DBDAOFactory.getDAOFactory();
//		this.daoMan = this.daoFact.getManagerDiSistemaDAO();
	}

	@Override
	public boolean inserisciManagerDiSistema(UtenteTO uto) {
		return false;
	}

	
}
