package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.OptionalDAO;
import agroludos.to.OptionalTO;

class MySqlOptionalDAO extends MySqlAgroludosDAO implements OptionalDAO {

	@Override
	public boolean crea(OptionalTO optTO) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(optTO);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean update(OptionalTO optTO) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("updateOptional");
		query.setParameter("nome", optTO.getNome());
		query.setParameter("descrizione", optTO.getDescrizione());
		query.setParameter("tipo", optTO.getTipo());
		query.setParameter("costo", optTO.getCosto());
		query.setParameter("stato", optTO.getStato());
		query.setParameter("id", optTO.getId());

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
	public List<OptionalTO> readByTipo(OptionalTO optTO) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getOptionalByTipo");
		query.setParameter("tipo", optTO.getTipo());
		List<OptionalTO> list = query.list();
		return list;
	}

	@Override
	public boolean eliminaOptional(OptionalTO optTO) {
		boolean res = false;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("eliminaOptional");
		query.setParameter("stato", 0);
		query.setParameter("id", optTO.getId());

		if(query.executeUpdate() == 1){
			res = true;
		}

		this.session.getTransaction().commit();

		return res;
	}

}
