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
	public <T>List<CompetizioneTO> readByTipo(T tipo) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioneByTipo");
		query.setParameter("tipo", tipo);
		List<CompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public <T> List<CompetizioneTO> readByMdc(T mdc) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioniByMdc");
		query.setParameter("mdc", mdc);
		List<CompetizioneTO> list = query.list();
		return list;
	}
	
	@Override
	public <T> CompetizioneTO readById(T id) {
		CompetizioneTO res=null;
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioneById");
		query.setParameter("id", id);
		List<CompetizioneTO> list = query.list();
		res = list.get(0);
		return res;
	}

	@Override
	public List<CompetizioneTO> readCompetizioniAttive() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioniAttive");
		query.setParameter("stato", 1);
		List<CompetizioneTO> list = query.list();
		return list;
	}

}
