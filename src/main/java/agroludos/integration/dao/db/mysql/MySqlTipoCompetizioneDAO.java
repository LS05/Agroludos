package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;

import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class MySqlTipoCompetizioneDAO extends MySqlAgroludosDAO<TipoCompetizioneTO> implements TipoCompetizioneDAO {

	MySqlTipoCompetizioneDAO(SessionFactory sessionFactory){
		super(sessionFactory);
		this.setClasse(TipoCompetizioneTO.class);
	}

}