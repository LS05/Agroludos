package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;

public interface DBFactory {

	DBDAOFactory getDAOFactory(String tipo) throws DatabaseException;

}