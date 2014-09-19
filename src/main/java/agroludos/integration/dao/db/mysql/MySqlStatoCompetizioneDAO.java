package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.StatoCompetizioneDAO;
import agroludos.to.StatoCompetizioneTO;

public class MySqlStatoCompetizioneDAO extends MySqlAgroludosDAO<StatoCompetizioneTO> implements StatoCompetizioneDAO {

	public MySqlStatoCompetizioneDAO(Session session) {
		super(session);
		this.setClasse(StatoCompetizioneTO.class);
	}

	

}
