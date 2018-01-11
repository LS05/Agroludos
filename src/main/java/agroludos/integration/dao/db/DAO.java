package agroludos.integration.dao.db;

import java.io.Serializable;
import java.util.List;

import agroludos.exceptions.system.DatabaseException;

/**
 * <b>Integration Tier</b>
 * L'interfaccia rappresenta una generalizzazione del pattern DAO per tutti i DAO presenti all'interno del package
 * agroludos.integration.dao.db.<br/>
 * In particolare la generalizzazione fa riferimento a tutte le operazioni comuni che è possibile eseguire su un
 * database, ovvero: inserimento, modifica, cancellazione e lettura (CRUD). Sono forniti metodi di supporto
 * per ritrovare una singola entità oppure tutte le entità di un particolare tipo.<br/>
 * Inoltre sono forniti anche metodi per l'esecuzione di una query in base al suo nome. Il nome sarà utile in quanto
 * grazie a questo è possibile ottenere la query (presente in una risorsa esterna)
 * su cui effettuare le dovute sostituzioni soprattutto nel caso di query parametriche. 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 * @param <T> Specifica il tipo di entità da utilizare
 */
public interface DAO<T extends Serializable> {

	/**
	 * Memorizza un'istanza di tipo T nel database
	 * 
	 * @param entity L'entità da memorizzare
	 * @return
	 * @throws DatabaseException
	 */
	public T create(final T entity) throws DatabaseException;

	/**
	 * Restituisce una singola istanza di tipo T
	 * @param id id dell'istanza
	 * @return
	 * @throws DatabaseException
	 */
	public T findOne( final Integer id ) throws DatabaseException;

	/**
	 * Ritorna tutte le entità di tipo T
	 * @return Una lista contenente tutte le istanze di tipo T
	 * @throws DatabaseException
	 */
	public List< T > getAll() throws DatabaseException;

	/**
	 * Modifica un'entità di tipo T
	 * 
	 * @param entity L'entità da modificare
	 * @return
	 * @throws DatabaseException
	 */
	public T update( final T entity ) throws DatabaseException;

	/**
	 * Esegue la query identificata da queryName a cui verranno passati i parametri presenti nella lista
	 * parameters.
	 * 
	 * @param queryName Nome della query
	 * @param parameters Parametri da utilizzare nella query
	 * @return Una lista di entità risultato della query.
	 * 
	 * @throws DatabaseException
	 */
	<P> List<T> executeParamQuery(String queryName, List<P> parameters) throws DatabaseException;

	/**
	 * Esegue la query identificata da queryName che rappresenta una query non parametrica.
	 * 
	 * @param queryName Nome della query
	 * @return Una lista di entità risultato della query.
	 * @throws DatabaseException
	 */
	List<T> executeQuery(String queryName) throws DatabaseException;

}