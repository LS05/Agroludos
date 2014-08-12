package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;

public class MySqlConfigurazioneDAO extends MySqlDAO implements DBConfigurazioneDAO {
	private Session session;
	
	MySqlConfigurazioneDAO(){
		this.session = getSessionFactory().openSession();
	}
	
	@Override
	public boolean addConfigurazioneDB(ConfigurazioneTO conf) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(conf);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean getStatoConfigurazione() {
		// TODO Auto-generated method stub
		return false;
	}

}
