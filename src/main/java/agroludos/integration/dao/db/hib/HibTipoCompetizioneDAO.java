package agroludos.integration.dao.db.hib;

import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class HibTipoCompetizioneDAO extends HibAgroludosDAO<TipoCompetizioneTO> implements TipoCompetizioneDAO {

	HibTipoCompetizioneDAO(){
		this.setClasse(toFact.createTipoCompetizioneTO());
	}

}