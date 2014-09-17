package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;

import agroludos.integration.dao.db.TipoOptionalDAO;
import agroludos.to.TipoOptionalTO;

class MySqlTipoOptionalDAO extends MySqlAgroludosDAO<TipoOptionalTO> implements TipoOptionalDAO {

	MySqlTipoOptionalDAO(SessionFactory sessionFactory){
		super(sessionFactory);
		this.setClasse(TipoOptionalTO.class);
	}

}