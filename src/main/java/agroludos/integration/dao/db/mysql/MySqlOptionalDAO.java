package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {

	MySqlOptionalDAO(Session session){
		super(session);
		this.setClasse(OptionalTO.class);
	}

//	@Override
//	public List<OptionalTO> readByTipo(TipoOptionalTO optTO) throws DatabaseException {
//		List<String> param = new ArrayList<String>();
//		param.add(optTO.getNome());
//
//		List<OptionalTO> res = super.executeParamQuery("getOptionalByTipo", param);
//
//		return res;
//	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException {
		optTO.getStatoOptional().setId(0);
		return super.update(optTO);
	}
}