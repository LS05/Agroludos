package agroludos.integration.dao.db;

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
	 */
	public DBDAOFactory getDAOFactory(String tipo){
		DBDAOFactory res = null;
		
		if(tipo.toLowerCase().equals("mysql")){
			res = mySqlFact;
		}
		
		return res;
	}
}