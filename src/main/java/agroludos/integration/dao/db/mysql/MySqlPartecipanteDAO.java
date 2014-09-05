package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

class MySqlPartecipanteDAO extends MySqlAgroludosDAO implements PartecipanteDAO {

	@Override
	public boolean crea(PartecipanteTO mdcto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PartecipanteTO read(UtenteTO uto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> PartecipanteTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> PartecipanteTO readByID(T id) {
		PartecipanteTO res = null;
		this.session.beginTransaction();

		Query query = session.getNamedQuery("getPartecipanteById");
		query.setParameter("id", id);
		List<PartecipanteTO> lspart = query.list();
		res = lspart.get(0);
		this.session.getTransaction().commit();
		
		return res;

	}

	@Override
	public boolean update(PartecipanteTO parto) {
		// TODO Auto-generated method stub
		return false;
	}
}