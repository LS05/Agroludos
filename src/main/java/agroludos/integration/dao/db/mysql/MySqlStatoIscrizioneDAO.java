package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

public class MySqlStatoIscrizioneDAO extends MySqlAgroludosDAO<StatoIscrizioneTO> implements StatoIscrizioneDAO{

	public MySqlStatoIscrizioneDAO() {
		this.setClasse(StatoIscrizioneTO.class);
	}

}
