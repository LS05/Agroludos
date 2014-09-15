package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.IscrizioneDAO;
import agroludos.to.IscrizioneTO;

class MySqlIscrizioneDAO extends MySqlAgroludosDAO<IscrizioneTO> implements IscrizioneDAO{

	MySqlIscrizioneDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean crea(IscrizioneTO iscto) throws DatabaseException {
		return super.create(iscto);
	}

	@Override
	public boolean update(IscrizioneTO iscto) throws DatabaseException {
		return super.update(iscto);
	}

	@Override
	public boolean annullaIscrizione(IscrizioneTO iscto) throws DatabaseException {
		Transaction tx = null;
		boolean res = false;
		try {
			tx = this.session.beginTransaction();
			this.session.beginTransaction();

			Query query = session.getNamedQuery("annullaIscrizione");
			query.setParameter("stato", 0);
			query.setParameter("id", iscto.getId());

			if(query.executeUpdate() == 1){
				res = true;
			}

			this.session.getTransaction().commit();
		} catch (HibernateException e){
			if (tx != null) tx.rollback();
			throw new DatabaseException(e.getMessage(), e);
		}
		return res;
	}

	@Override
	public List<IscrizioneTO> getAllIscrizioni() throws DatabaseException {
		return super.executeQuery("getAllIscrizioni");
	}

}
