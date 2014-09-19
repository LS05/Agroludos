package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

public class MySqlStatoIscrizioneDAO extends MySqlAgroludosDAO<StatoIscrizioneTO> implements StatoIscrizioneDAO{

	public MySqlStatoIscrizioneDAO(Session session) {
		super(session);
		this.setClasse(StatoIscrizioneTO.class);
	}

}
