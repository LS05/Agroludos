package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO implements CompetizioneDAO {

	@Override
	public boolean crea(CompetizioneTO cmpto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(cmpto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean update(CompetizioneTO cmpto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("updateCompetizione");
		query.setParameter("nome", cmpto.getNome());
		query.setParameter("data", cmpto.getData());
		query.setParameter("nmin", cmpto.getNmin());
		query.setParameter("nmax", cmpto.getNmax());
		query.setParameter("costo", cmpto.getCosto());
		query.setParameter("descrizione", cmpto.getDescrizione());
		query.setParameter("tipo", cmpto.getTipo());
		query.setParameter("stato", cmpto.getStato());
		query.setParameter("id", cmpto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

	@Override
	public boolean annullaCompetizione(CompetizioneTO cmpto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("annullaCompetizione");
		query.setParameter("stato", 0);
		query.setParameter("id", cmpto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

	@Override
	public List<CompetizioneTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllCompetizione");
		List<CompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public List<CompetizioneTO> readByTipo(CompetizioneTO cmpto) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioneByTipo");
		query.setParameter("tipo", cmpto.getTipo());
		List<CompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public List<CompetizioneTO> readByMdc(CompetizioneTO cmpto) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioneByTipo");
		query.setParameter("mdc", cmpto.getMdc());
		List<CompetizioneTO> list = query.list();
		return list;
	}
	
	@Override
	public List<CompetizioneTO> readById(CompetizioneTO cmpto) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioneById");
		query.setParameter("id", cmpto.getId());
		List<CompetizioneTO> list = query.list();
		return list;
	}

}
