package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {

	MySqlOptionalDAO(Session session){
		super(session);
		this.setClasse(OptionalTO.class);
	}

	@Override
	public List<OptionalTO> readByTipo(TipoOptionalTO optTO) throws DatabaseException {
		List<TipoOptionalTO> param = new ArrayList<TipoOptionalTO>();
		param.add(optTO);

		List<OptionalTO> res = super.executeParamQuery("getOptionalByTipo", param);

		return res;
	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException {
		optTO.getStatoOptional().setId(0);
		return super.update(optTO);
	}
}