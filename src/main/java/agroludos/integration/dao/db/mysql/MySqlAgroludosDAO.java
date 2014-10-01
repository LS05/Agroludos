package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DAO;
import agroludos.to.AgroludosTO;
import agroludos.to.TOFactory;

abstract class MySqlAgroludosDAO<T extends AgroludosTO> implements DAO<T> {

	protected Session session;

	protected TOFactory toFact;

	private Class< T > classe;

	MySqlAgroludosDAO(){ }

	MySqlAgroludosDAO(Session session){
		this.session = session;
	}

	protected void setClasse( final Class< T > classToSet ){
		classe = classToSet;
	}

	@Override
	public List< T > getAll() throws DatabaseException {
		List<T> res = null;

		try{
			res = this.session.createQuery( "from " + this.classe.getName() ).list();
		}catch (HibernateException e){
			throw new DatabaseException(e.getMessage(), e);
		}

		return res; 
	}

	@Override
	public T findOne(long id) throws DatabaseException {
		T entity = null;

		try{
			entity = (T) this.session.get( this.classe, id );
		} catch (HibernateException e){
			throw new DatabaseException(e.getMessage(), e);
		}

		return entity; 
	}

	@Override
	public T create(final T mainTO) throws DatabaseException{
		Transaction tx = null;

		try {
			tx = this.session.beginTransaction();

			this.session.save(mainTO);

			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}
		session.refresh(mainTO);
		return mainTO;
	}

	@Override
	public T update(final T entity) throws DatabaseException{
		Transaction tx = null;

		try {
			tx = this.session.beginTransaction();
			this.session.update(entity);
			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}
		session.refresh(entity);
		return entity;		
	}

	@Override
	public <P> List<T> executeParamQuery(String queryName, List<P> parameters) throws DatabaseException {
		Transaction tx = null;
		List<T> res = null;

		try {
			tx = this.session.beginTransaction();

			Query query = this.session.getNamedQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, param);
				index++;
			}

			res = query.list();

			this.session.getTransaction().commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res;
	}

	@Override
	public <V> V executeValParamQuery(String queryName, List<?> parameters) throws DatabaseException {
		Transaction tx = null;
		List<V> res = null;

		try {
			tx = this.session.beginTransaction();

			Query query = this.session.getNamedQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, param);
				index++;
			}

			res = query.list();

			this.session.getTransaction().commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res.get(0);
	}


	@Override
	public List<T> executeQuery(String queryName) throws DatabaseException {
		Transaction tx = null;
		List<T> res = null;

		try {
			tx = this.session.beginTransaction();

			Query query = this.session.getNamedQuery(queryName);
			res = query.list();

			this.session.getTransaction().commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res;
	}

	@Override
	public <V> V executeValQuery(String queryName) throws DatabaseException {
		Transaction tx = null;
		List<V> res = null;

		try {
			tx = this.session.beginTransaction();

			Query query = this.session.getNamedQuery(queryName);
			res = query.list();

			this.session.getTransaction().commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res.get(0);
	}

	protected void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}
}