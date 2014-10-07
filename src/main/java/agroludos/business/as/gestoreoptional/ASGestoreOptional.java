package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.integration.dao.db.StatoOptionalDAO;
import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.TipoOptionalTO;

class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	private OptionalDAO getOptionalDAO() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getOptionalDAO();
	}
	
	private TipoOptionalDAO getTipoOptionalDAO() throws DatabaseException{
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		return dbDAOFact.getTipoOptionalDAO();
	}

	@Override
	public OptionalTO inserisciOptional(OptionalTO optto) throws DatabaseException {

		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.create(optto);

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

		TipoOptionalDAO daoTipo = this.getTipoOptionalDAO();
		List<TipoOptionalTO> listTipi = daoTipo.getAll();
		List<OptionalTO> res = null;
		
		for(TipoOptionalTO tipo : listTipi){
			if(tipo.getNome().equals(optto.getNome())){
				res = tipo.getAllOptionals();
				break;
			}
		}

		return res;
	}

	@Override
	public List<OptionalTO> getAllOptional() throws DatabaseException {
		OptionalDAO daoOpt = getOptionalDAO();
		return daoOpt.getAll();
	}
	
	@Override
	public List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException {
		TipoOptionalDAO daoOpt = this.getTipoOptionalDAO();
		return daoOpt.getAll();
	}

	@Override
	public List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException {
		DBDAOFactory dbDAOFact = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		StatoOptionalDAO statoOpt = dbDAOFact.getStatoOptionalDAO();
		return statoOpt.getAll();
	}
}