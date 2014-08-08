package agroludos.business.as.gestoremds;

import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.UtenteTO;

class ASGestoreManagerDiSistema implements LManagerDiSistema, SManagerDiSistema{
	private DBDAOFactory daoFact;
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
