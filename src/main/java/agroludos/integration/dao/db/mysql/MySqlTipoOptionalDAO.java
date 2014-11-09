package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class MySqlTipoOptionalDAO extends MySqlAgroludosDAO<TipoOptionalTO> implements TipoOptionalDAO {

	MySqlTipoOptionalDAO(){
		this.setClasse(toFact.createTipoOptionalTO());
	}

}