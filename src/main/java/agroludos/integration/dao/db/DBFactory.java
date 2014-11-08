package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;

public interface DBFactory {

	DBDAOFactory getDAOFactory(String tipo) throws DatabaseException;

}