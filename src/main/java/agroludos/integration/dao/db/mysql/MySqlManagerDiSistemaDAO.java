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
	public boolean crea(ManagerDiSistemaTO mdsto) throws DatabaseException {
		return super.crea(mdsto);
	}

	@Override
	public ManagerDiSistemaTO readByUsername(String username) throws DatabaseException {
		return (ManagerDiSistemaTO) super.getByUsername(username);
	}
}