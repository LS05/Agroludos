package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO implements ManagerDiCompetizioneDAO{

	@Override
	public <T> ManagerDiCompetizioneTO readByUsername(T username) {
		return (ManagerDiCompetizioneTO) this.readByUsername(username, 1);
	}

	@Override
	public <T> ManagerDiCompetizioneTO readByID(T id) {
		return (ManagerDiCompetizioneTO) super.readByID(id, 1);
	}

	@Override
	public List<ManagerDiCompetizioneTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllManagerDiCompetizione");
		List<ManagerDiCompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public boolean crea(ManagerDiCompetizioneTO mdcto) {
		return super.crea(mdcto);
	}

	@Override
	public boolean update(ManagerDiCompetizioneTO mdcto) {
		return this.update(mdcto);
	}

	@Override
	public boolean delete(ManagerDiCompetizioneTO mdcto) {
		return this.update(mdcto);
	}
}