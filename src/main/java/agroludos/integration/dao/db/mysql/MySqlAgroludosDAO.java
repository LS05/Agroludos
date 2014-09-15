package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.to.AgroludosTO;
import agroludos.to.TOFactory;

class MySqlAgroludosDAO {
	protected Session session;
	protected TOFactory toFact;

	MySqlAgroludosDAO(){
		this.session = MySqlDAO.getSessionFactory().openSession();
	}

	protected boolean create(AgroludosTO mainTO) throws DatabaseException{
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

	protected boolean delete(AgroludosTO mainTO) throws DatabaseException{
		Transaction tx = null;
		boolean res = false;

		try {
			tx = this.session.beginTransaction();

			this.session.delete(mainTO);

			res = true;
			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}

		return res;
	}

	protected boolean update(AgroludosTO mainTO) throws DatabaseException{
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

	protected List<AgroludosTO> executeParamQuery(String queryName, List<?> parameters) throws DatabaseException {
		Transaction tx = null;
		List<AgroludosTO> res = null;

		try {
			tx = this.session.beginTransaction();
			Query query = this.session.getNamedQuery(queryName);
			int index = 0;

			for(Object param : parameters){
				query.setParameter(index, parameters.get(index));
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

	protected List<AgroludosTO> executeQuery(String queryName) throws DatabaseException {
		Transaction tx = null;
		List<AgroludosTO> res = null;

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

	protected void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}
}