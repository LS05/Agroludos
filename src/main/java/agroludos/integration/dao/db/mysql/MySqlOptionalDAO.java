package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO<OptionalTO> implements OptionalDAO {

	MySqlOptionalDAO(){
		this.setClasse(OptionalTO.class);
	}

	@Override
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException {
		return super.update(optTO);
	}
}