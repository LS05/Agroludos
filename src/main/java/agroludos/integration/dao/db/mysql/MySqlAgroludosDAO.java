package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.to.AgroludosTO;
import agroludos.to.TOFactory;

abstract class MySqlAgroludosDAO<T extends AgroludosTO> {
	
	protected Session session;
	protected TOFactory toFact;
	
	MySqlAgroludosDAO(){ }

	MySqlAgroludosDAO(SessionFactory sessionFactory){
		this.session = MySqlDAO.getSessionFactory().openSession();
	}

	protected boolean create(T mainTO) throws DatabaseException{
		Transaction tx = null;
		boolean res = false;

		try {
			tx = this.session.beginTransaction();

			this.session.save(mainTO);

			res = true;
			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res;
	}


	protected boolean update(T mainTO) throws DatabaseException{
		Transaction tx = null;
		boolean res = false;

		try {
			tx = this.session.beginTransaction();

			this.session.update(mainTO);

			res = true;
			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res;		
	}

	protected List<T> executeParamQuery(String queryName, List<?> parameters) throws DatabaseException {
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

	protected List<T> executeQuery(String queryName) throws DatabaseException {
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

	protected String executeParamStringQuery(String queryName, List<?> parameters) throws DatabaseException {
		Transaction tx = null;
		List<String> res = null;

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
	
	protected void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}
}