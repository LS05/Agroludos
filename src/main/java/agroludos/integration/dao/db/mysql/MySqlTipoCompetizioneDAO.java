package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class MySqlTipoCompetizioneDAO extends MySqlAgroludosDAO<TipoCompetizioneTO> implements TipoCompetizioneDAO {

	MySqlTipoCompetizioneDAO(){
		this.setClasse(TipoCompetizioneTO.class);
	}

}