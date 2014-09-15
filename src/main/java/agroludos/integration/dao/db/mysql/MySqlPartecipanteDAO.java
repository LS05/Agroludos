package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

class MySqlPartecipanteDAO extends MySqlUtenteDAO implements PartecipanteDAO {

	@Override
	public boolean crea(PartecipanteTO parto) throws DatabaseException {
		return super.crea(parto);
	}


	@Override
	public <T> PartecipanteTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> PartecipanteTO readByID(T id) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getPartecipante");
		query.setParameter("id", id);
		List<PartecipanteTO> list = query.list();
		return list.get(0);

	}

	@Override
	public boolean update(PartecipanteTO parto) throws DatabaseException {
		return super.update(parto);
	}


	@Override
	public boolean delete(PartecipanteTO parto) throws DatabaseException {
		return super.delete(parto);
	}


	@Override
	public List<PartecipanteTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllPartecipanti");
		List<PartecipanteTO> list = query.list();
		return list;
	}


	@Override
	public <T> PartecipanteTO readByCF(T cf) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getPartecipanteByCF");
		query.setParameter("cf", cf);
		List<PartecipanteTO> list = query.list();
		return list.get(0);
	}
}