package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.TipoCompetizioneDAO;
import agroludos.to.TipoCompetizioneTO;

class MySqlTipoCompetizioneDAO extends MySqlAgroludosDAO implements TipoCompetizioneDAO {

	@Override
	public boolean crea(TipoCompetizioneTO tcpto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(tcpto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public List<TipoCompetizioneTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllTipoCompetizione");
		List<TipoCompetizioneTO> list = query.list();
		return list;
	}

}
