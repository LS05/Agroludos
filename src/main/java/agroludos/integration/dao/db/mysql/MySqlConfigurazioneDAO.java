package agroludos.integration.dao.db.mysql;

import org.hibernate.Transaction;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;

class MySqlConfigurazioneDAO extends MySqlAgroludosDAO implements DBConfigurazioneDAO {

	@Override
	public boolean addConfigurazioneDB(ConfigurazioneTO conf) throws DatabaseException {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		Transaction tx = null;
		
		try {
			tx = this.session.beginTransaction();
			this.session.save(conf);
			res = true;
			this.session.getTransaction().commit();
		} catch (Exception e){
		     if (tx != null) tx.rollback();
		     throw new DatabaseException(e.getMessage(), e);
		}
		
		return res;
	}
}