package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class ASGestoreManagerDiSistema extends AgroludosAS implements LManagerDiSistema, SManagerDiSistema{
	
	@Override
	public boolean inserisciManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException {
		// TODO Auto-generated catch block
		// Catturare l'eccezione del DB perchè un mancato inserimento del MDS significherebbe
		// resettare il tipo di DB nel file xml
		boolean res = getManagerDiSistemaDAO().crea(mdsto);
		return res;
	}

	@Override
	public ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO uto) throws DatabaseException {
		return getManagerDiSistemaDAO().read(uto);
	}
	
	private ManagerDiSistemaDAO getManagerDiSistemaDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getManagerDiSistemaDAO();
	}

}