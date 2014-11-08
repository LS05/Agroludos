package agroludos.integration.dao.db;

import agroludos.exceptions.DBFactoryException;
import agroludos.exceptions.DatabaseException;
import agroludos.system.SystemConf;

class DBFactoryImpl implements DBFactory{

	private DBDAOFactory fact;
	private SystemConf sysConf;
	
	//TODO da rivedere
	public DBFactoryImpl(DBDAOFactory fact, SystemConf sysConf){
		this.fact = fact;
		this.sysConf = sysConf;
	}

	/**
	 * In base al parametro di input il metodo ritorna una delle possibili
	 * implementazioni di questo factory, basate sulla specifica
	 * dell'interfaccia DBDAOFactory e sul tipo di database impostato in
	 * configurazione. Altrimenti se il tipo non è supportato solleva una
	 * DBFactoryException
	 * @param tipo
	 * @return
	 * @throws DatabaseException
	 */
	@Override
	public DBDAOFactory getDAOFactory(String tipo) throws DatabaseException{
		DBDAOFactory res = null;

		if(tipo.equalsIgnoreCase( this.sysConf.getTipoDB() )){
			res = this.fact;
		} else {
			throw new DBFactoryException();
		}

		return res;
	}
}