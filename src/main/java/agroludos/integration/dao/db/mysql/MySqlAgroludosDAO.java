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

	protected static TOFactory toFact;

	private AgroludosTO classe;

	protected void setClasse( final AgroludosTO agroTO ){
		classe = agroTO;
	}

	private Session getSession(){
		return MySqlDAOUtil.getSessionFactory().openSession();
	}

	@Override
	public List< T > getAll() throws DatabaseException {
		List<T> res = null;
		Transaction tx = null;
		Session session = null;

		try{
			session = this.getSession();
			tx = session.beginTransaction();

			res = session.createQuery( "from " + this.classe.getClass().getName() ).list();

			tx.commit();
		}catch (HibernateException e){
			tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return res; 
	}

	@Override
	public T findOne(Integer id) throws DatabaseException {
		T entity = null;
		Transaction tx = null;
		Session session = null;

		try{
			session = this.getSession();
			tx = session.beginTransaction();

			entity = (T) session.get( this.classe.getClass().getName(), id );

			tx.commit();
		} catch (HibernateException e){
			tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return entity; 
	}

	@Override
	public T create(final T mainTO) throws DatabaseException{
		Transaction tx = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			session.saveOrUpdate(mainTO);

			tx.commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return mainTO;
	}

	@Override
	public T update(final T entity) throws DatabaseException{
		Transaction tx = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			session.update(entity);

			tx.commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return entity;		
	}

	@Override
	public <P> List<T> executeParamQuery(String queryName, List<P> parameters) throws DatabaseException {
		Transaction tx = null;
		List<T> res = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, param);
				index++;
			}

			res = query.list();

			tx.commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return res;
	}

	@Override
	public <V> V executeValParamQuery(String queryName, List<?> parameters) throws DatabaseException {
		Transaction tx = null;
		List<V> res = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, param);
				index++;
			}

			res = query.list();

			tx.commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return res.get(0);
	}


	@Override
	public List<T> executeQuery(String queryName) throws DatabaseException {
		Transaction tx = null;
		List<T> res = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			res = query.list();

			tx.commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return res;
	}

	@Override
	public <V> V executeValQuery(String queryName) throws DatabaseException {
		Transaction tx = null;
		List<V> res = null;
		Session session = null;

		try {
			session = this.getSession();
			tx = session.beginTransaction();

			Query query = session.getNamedQuery(queryName);
			res = query.list();

			tx.commit();
		} catch (Exception e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}finally{
			session.close();
		}

		return res.get(0);
	}

	public void setToFact(TOFactory toFactory) {
		toFact = toFactory;
	}
}