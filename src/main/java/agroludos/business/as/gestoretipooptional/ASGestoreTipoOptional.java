package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class ASGestoreTipoOptional extends AgroludosAS implements LTipoOptional, STipoOptional{

	@Override
	public boolean inserisciTipoOptional(TipoOptionalTO topto)
			throws DatabaseException {
		boolean res = false;

		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		res = daoTop.crea(topto);

		return res;
	}

	private TipoOptionalDAO getTipoOptionalDAO() {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoOptionalDAO();
	}

	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		return daoTop.readAll();
	}



}