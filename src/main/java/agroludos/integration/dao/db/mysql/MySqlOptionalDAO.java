package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {

	MySqlOptionalDAO(Session session){
		super(session);
		this.setClasse(OptionalTO.class);
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
	public OptionalTO eliminaOptional(OptionalTO optTO) throws DatabaseException {
		optTO.setStato(0);
		return super.update(optTO);
	}

	@Override
	public void setNomeTipoOpt(OptionalTO optTO) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(optTO.getTipo());
		String tipo = super.executeValParamQuery("getNomeTipoOpt", param);
		optTO.setNomeTipoOpt(tipo);
	}

	@Override
	public void setNomeStatoOpt(OptionalTO optTO) throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(optTO.getStato());
		String stato = super.executeValParamQuery("getNomeStatoOpt", param);
		optTO.setNomeStatoOpt(stato);
	}
}