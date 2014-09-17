package agroludos.integration.dao.db;

import agroludos.exceptions.DBFactoryException;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.mysql.MySqlDAOFactory;
import agroludos.integration.dao.db.mysql.MySqlDAOUtil;

public class DBFactory{
	private MySqlDAOFactory mySqlFact;

	public DBDAOFactory createMySqlDAOFactory(MySqlDAOUtil daoUtil) throws DatabaseException{
		this.mySqlFact = new MySqlDAOFactory(daoUtil);
		return this.mySqlFact;
	}

	/**
	 * In base al parametro di input il metodo ritorna una delle possibili
	 * implementazioni di questo factory, basate sulla specifica
	 * dell'interfaccia DBDAOFactory
	 * 
	 * @throws DatabaseException 
	 */
	public DBDAOFactory getDAOFactory(String tipo) throws DatabaseException{
		DBDAOFactory res = null;

		if(tipo.toLowerCase().equals("mysql")){
			if(this.mySqlFact.testConnection())
				res = this.mySqlFact;
		} else {
			throw new DBFactoryException();
		}

		return res;
	}
}