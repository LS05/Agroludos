package agroludos.business.as.gestoretipoutente;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoUtenteDAO;
import agroludos.to.TipoUtenteTO;

class ASGestoreTipoUtente extends AgroludosAS implements LTipoUtente{
	
	private TipoUtenteDAO getTipoUtenteDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoUtenteDAO();
	}

	@Override
	public List<TipoUtenteTO> getAllTipoUtente()
			throws DatabaseException {
		TipoUtenteDAO daoTUt = getTipoUtenteDAO();
		return daoTUt.getAll();
	}
}