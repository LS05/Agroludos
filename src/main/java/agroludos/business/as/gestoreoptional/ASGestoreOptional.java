package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	private OptionalDAO getOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getOptionalDAO();
	}

	@Override
	public boolean inserisciOptional(OptionalTO optto) throws DatabaseException {
		boolean res = false;

		OptionalDAO daoOpt = getOptionalDAO();
		res = daoOpt.create(optto);

		return res;
	}

	@Override
	public OptionalTO modificaOptional(OptionalTO optto) throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.update(optto);
	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optto) throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.disattivaOptional(optto);
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO optto)
			throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.readByTipo(optto);
	}

	@Override
	public List<OptionalTO> getAllOptional() throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.getAll();
	}
	
	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		TipoOptionalDAO daoOpt = dbDAOFact.getTipoOptionalDAO();
		return daoOpt.getAll();
	}
}