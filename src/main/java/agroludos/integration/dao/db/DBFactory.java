package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;

/**
 * L'interfaccia rappresenta l'Abstract DAO Factory. L'obiettivo è quello di ottenere un factory
 * in base al tipo di sorgente dati specificato come parametro del metodo getDAOFactory().
 * 
 * @see <a href="http://it.wikipedia.org/wiki/Abstract_factory">http://it.wikipedia.org/wiki/Abstract_factory</a>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface DBFactory {

	/**
	 * Il metodo si occupa di restituire una sorgente dati in base al tipo specificato
	 * 
	 * @param tipo Tipo di sorgente dati
	 * @return Factory in base al tipo specificato
	 * @throws DatabaseException
	 */
	DBDAOFactory getDAOFactory(String tipo) throws DatabaseException;

}