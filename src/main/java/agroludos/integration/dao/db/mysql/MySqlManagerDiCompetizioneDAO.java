package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO implements ManagerDiCompetizioneDAO{
	
	MySqlManagerDiCompetizioneDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public ManagerDiCompetizioneTO getByStipendio(Integer id) throws DatabaseException {
		return null;
	}
}