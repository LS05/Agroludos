package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;

public interface MySqlDAOUtil {
	SessionFactory buildSessionFactory() throws DatabaseException;
	SessionFactory getSessionFactory();
}
