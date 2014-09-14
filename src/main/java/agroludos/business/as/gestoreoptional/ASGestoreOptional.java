package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	private OptionalDAO getOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getOptionalDAO();
	}

	@Override
	public boolean inserisciOptional(OptionalTO optto) throws DatabaseException {
		boolean res = false;

		OptionalDAO daoOpt = getOptionalDAO();
		res = daoOpt.crea(optto);

		return res;
	}

	@Override
	public boolean modificaOptional(OptionalTO optto) throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.update(optto);
	}

	@Override
	public boolean eliminaOptional(OptionalTO optto) throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.eliminaOptional(optto);
	}

	@Override
	public List<OptionalTO> getOptionalByTipo(OptionalTO optto)
			throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.readByTipo(optto);
	}

	@Override
	public List<OptionalTO> getAllOptional() throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.readAll();
	}
}