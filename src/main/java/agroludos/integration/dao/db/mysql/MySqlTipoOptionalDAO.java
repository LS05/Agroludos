package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class MySqlTipoOptionalDAO extends MySqlAgroludosDAO<TipoOptionalTO> implements TipoOptionalDAO {

	MySqlTipoOptionalDAO(Session session){
		super(session);
		this.setClasse(TipoOptionalTO.class);
	}

}