package agroludos.integration.dao.db;

import agroludos.exceptions.DBFactoryException;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.mysql.MySqlDAOFactory;


public class DBFactory{
	private static MySqlDAOFactory mySqlFact = new MySqlDAOFactory();
	
	DBDAOFactory createMySqlDAOFactory(){
		return mySqlFact;
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
			try {
				if(mySqlFact.initialize())
					res = mySqlFact;
			} catch (DatabaseException e) {
				throw new DatabaseException(e.getMessage());
			}
		} else {
			throw new DBFactoryException("Tipo Database non riconosciuto.");
		}
		
		return res;
	}
}