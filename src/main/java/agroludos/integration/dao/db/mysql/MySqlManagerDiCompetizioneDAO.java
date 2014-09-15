package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO implements ManagerDiCompetizioneDAO{

	@Override
	public ManagerDiCompetizioneTO getByUsername(String username) {
		return (ManagerDiCompetizioneTO) this.getByUsername(username);
	}

	@Override
	public ManagerDiCompetizioneTO readByID(Integer id) throws DatabaseException {
		return (ManagerDiCompetizioneTO) super.readByID(id);
	}

	@Override
	public boolean crea(ManagerDiCompetizioneTO mdcto) throws DatabaseException {
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
	
	@Override
	public List<ManagerDiCompetizioneTO> readAll() {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getAllManagerDiCompetizione");
		List<ManagerDiCompetizioneTO> list = query.list();
		return list;
	}
}