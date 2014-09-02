package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO implements OptionalDAO {

	@Override
	public boolean crea(OptionalTO optto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(optto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean update(OptionalTO optto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("updateOptional");
		query.setParameter("nome", optto.getNome());
		query.setParameter("descrizione", optto.getDescrizione());
		query.setParameter("tipo", optto.getTipo());
		query.setParameter("costo", optto.getCosto());
		query.setParameter("stato", optto.getStato());
		query.setParameter("id", optto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

	@Override
	public List<OptionalTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllOptional");
		List<OptionalTO> list = query.list();
		return list;
	}

	@Override
	public List<OptionalTO> readByTipo(Integer tipo) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getOptionalByTipo");
		query.setParameter("tipo", tipo);
		List<OptionalTO> list = query.list();
		return list;
	}

	@Override
	public boolean eliminaOptional(OptionalTO optto) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("eliminaOptional");
		query.setParameter("stato", 0);
		query.setParameter("id", optto.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

}
