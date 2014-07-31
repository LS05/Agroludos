package agroludos.integration.dao.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;

public class MySqlConfigurazioneDAO implements ConfigurazioneDAO {
	private Session session;
	
	MySqlConfigurazioneDAO(){
		this.session = MySqlDAOFactory.getSession();
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
