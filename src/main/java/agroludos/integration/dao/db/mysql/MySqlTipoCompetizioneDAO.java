package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class MySqlTipoCompetizioneDAO extends MySqlAgroludosDAO<TipoCompetizioneTO> implements TipoCompetizioneDAO {

	MySqlTipoCompetizioneDAO(Session session){
		super(session);
		this.setClasse(TipoCompetizioneTO.class);
	}

}