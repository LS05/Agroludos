package agroludos.integration.dao.mysql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;

public class MySqlConfigurazioneDAO implements FConfigurazioneDAO {
	private Session session;
	
	MySqlConfigurazioneDAO(){
		this.session = MySqlDAOFactory.getSession();
	}
	
	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {
		return false;
	}

	@Override
	public boolean salvaConfigurazione(ConfigurazioneTO conf) {
		
		this.session.beginTransaction();
		this.session.save(conf);
		this.session.getTransaction().commit();
		
		return false;
	}

}
