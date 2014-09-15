package agroludos.integration.dao.db.mysql;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.CompetizioneDAO;
import agroludos.to.CompetizioneTO;

class MySqlCompetizioneDAO extends MySqlAgroludosDAO<CompetizioneTO> implements CompetizioneDAO {

	MySqlCompetizioneDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public boolean crea(CompetizioneTO cmpto) throws DatabaseException {
		return super.create(cmpto);
	}

	@Override
	public boolean update(CompetizioneTO cmpto) throws DatabaseException {
		return super.update(cmpto);
	}

	@Override
	public boolean annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException {
		Transaction tx = null;
		boolean res = false;

		try {
			tx = this.session.beginTransaction();

			Query query = session.getNamedQuery("annullaCompetizione");
			query.setParameter("stato", 0);
			query.setParameter("id", cmpto.getId());

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
	public List<CompetizioneTO> readAll() throws DatabaseException {
		List<CompetizioneTO> res = super.executeQuery("getAllCompetizione");
		return  res;
	}

	@Override
	public List<CompetizioneTO> readByTipo(Integer tipo) throws DatabaseException{

		List<Integer> param = new ArrayList<Integer>();
		param.add(tipo);

		List<CompetizioneTO> res = super.executeParamQuery("getCompetizioneByTipo", param);

		return res;
	}

	@Override
	public <T> List<CompetizioneTO> readByMdc(T mdc) {
		this.session.beginTransaction();
		Query query = this.session.getNamedQuery("getCompetizioniByMdc");
		query.setParameter("mdc", mdc);
		List<CompetizioneTO> list = query.list();
		return list;
	}

	@Override
	public CompetizioneTO readById(Integer id) throws DatabaseException{
		CompetizioneTO res = null;
		List<Integer> param = new ArrayList<Integer>();
		param.add(id);

		List<CompetizioneTO> list = super.executeParamQuery("getCompetizioneById", param);
		res = list.get(0);

		return res;
	}

	@Override
	public List<CompetizioneTO> readCompetizioniAttive() throws DatabaseException {
		List<Integer> param = new ArrayList<Integer>();
		param.add(1);

		List<CompetizioneTO> list = super.executeParamQuery("getCompetizioniAttive", param);

		return list;
	}

}
