package agroludos.integration.dao.db;

import java.io.Serializable;
import java.util.List;

import agroludos.exceptions.DatabaseException;

public interface DAO<T extends Serializable> {

	public boolean create(final T entity) throws DatabaseException;

	public T findOne( final long id ) throws DatabaseException;

	public List< T > getAll() throws DatabaseException;

	public T update( final T entity ) throws DatabaseException;

	<P> List<T> executeParamQuery(String queryName, List<P> parameters) throws DatabaseException;

	List<T> executeQuery(String queryName) throws DatabaseException;

	<V> V executeValParamQuery(String queryName, List<?> parameters) throws DatabaseException;

	<V> V executeValQuery(String queryName) throws DatabaseException;
	
}