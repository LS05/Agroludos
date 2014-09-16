package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {
	
	MySqlOptionalDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public boolean crea(OptionalTO optTO) throws DatabaseException {
		return super.create(optTO);
	}

	@Override
	public boolean update(OptionalTO optTO) throws DatabaseException {
		return super.update(optTO);
	}

	@Override
	public List<OptionalTO> readAll() throws DatabaseException {
		List<OptionalTO> res = super.executeQuery("getAllOptional");
		
		for(OptionalTO opt: res){
			this.setNomeStatoOpt(opt);
			this.setNomeTipoOpt(opt);
		}
		
		return res;
	}

	@Override
	public List<OptionalTO> readByTipo(OptionalTO optTO) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(optTO.getTipo());

		List<OptionalTO> res = super.executeParamQuery("getOptionalByTipo", param);

		for(OptionalTO opt: res){
			this.setNomeStatoOpt(opt);
			this.setNomeTipoOpt(opt);
		}
		
		return res;
	}

	@Override
	public boolean eliminaOptional(OptionalTO optTO) throws DatabaseException {
		optTO.setStato(0);
		return super.update(optTO);
	}
	
	@Override
	public void setNomeTipoOpt(OptionalTO optTO) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(optTO.getTipo());
		optTO.setNomeTipoOpt(super.executeParamStringQuery("getNomeTipoOpt", param));
	}

	@Override
	public void setNomeStatoOpt(OptionalTO optTO) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(optTO.getStato());
		optTO.setNomeStatoOpt(super.executeParamStringQuery("getNomeStatoOpt", param));
	}
	
}