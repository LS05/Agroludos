package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class MySqlManagerDiSistemaDAO extends MySqlUtenteDAO implements ManagerDiSistemaDAO{

	MySqlManagerDiSistemaDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}

	@Override
	public ManagerDiSistemaTO getByTelefono(String telefono) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}
}