package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.UtenteTO;

class MySqlManagerDiCompetizioneDAO extends MySqlAgroludosDAO implements ManagerDiCompetizioneDAO{

	@Override
	public boolean crea(ManagerDiCompetizioneTO mdcto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(mdcto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public ManagerDiCompetizioneTO read(UtenteTO uto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ManagerDiCompetizioneTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> ManagerDiCompetizioneTO readByID(T id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ManagerDiCompetizioneTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllManagerDiCompetizione");
		List<ManagerDiCompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public boolean update(ManagerDiCompetizioneTO mdcto) {
		this.session.beginTransaction();
		Query query = session.getNamedQuery("updateManagerDiCompetizione");
		query.setParameter("nome", mdcto.getNome());
		query.setParameter("cognome", mdcto.getCognome());
		query.setParameter("username", mdcto.getUsername());
		query.setParameter("email", mdcto.getEmail());
		query.setParameter("id", mdcto.getId());
		int result = query.executeUpdate();
		this.session.getTransaction().commit();
		return false;
	}
}