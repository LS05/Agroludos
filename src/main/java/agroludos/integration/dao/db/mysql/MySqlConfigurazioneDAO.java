package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Query;

import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.ManagerDiCompetizioneTO;

public class MySqlConfigurazioneDAO extends MySqlAgroludosDAO implements DBConfigurazioneDAO {

	@Override
	public boolean addConfigurazioneDB(ConfigurazioneTO conf) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		
		Query query = this.session.getNamedQuery("getRuoloUtente");
		List<String> test = query.list();
		
		System.out.println(test);

		
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
