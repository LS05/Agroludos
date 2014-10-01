package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class ASGestoreTipoOptional extends AgroludosAS implements LTipoOptional, STipoOptional{
	
	private TipoOptionalDAO getTipoOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoOptionalDAO();
	}

	@Override
	public TipoOptionalTO inserisciTipoOptional(TipoOptionalTO topto)
			throws DatabaseException {

		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		return daoTop.create(topto);
	}

	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		TipoOptionalDAO daoTop = getTipoOptionalDAO();
		return daoTop.getAll();
	}
}