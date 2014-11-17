package agroludos.integration.dao.db;

import agroludos.exceptions.system.DBFactoryException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.system.SystemConf;

/**
 * La classe implementa l'interfaccia DBFactory. E' utile per restituire il tipo di factory per accedere al
 * DAO per il database.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class DBFactoryImpl implements DBFactory{

	private DBDAOFactory fact;
	
	/**
	 * Classe di supporto per ottenere informazioni di sistema.
	 */
	private SystemConf sysConf;
	
	//TODO da rivedere
	DBFactoryImpl(DBDAOFactory fact, SystemConf sysConf){
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