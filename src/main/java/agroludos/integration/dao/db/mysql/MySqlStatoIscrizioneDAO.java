package agroludos.integration.dao.db.mysql;


import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.StatoIscrizioneDAO;
import agroludos.to.StatoIscrizioneTO;

public class MySqlStatoIscrizioneDAO extends MySqlAgroludosDAO<StatoIscrizioneTO> implements StatoIscrizioneDAO{

	public MySqlStatoIscrizioneDAO() {
		this.setClasse(toFact.createStatoIscrizioneTO());
	}

	@Override
	public StatoIscrizioneTO getStatoAttivo() throws DatabaseException {
		return this.findOne(1);
	}

	@Override
	public StatoIscrizioneTO getStatoDisattivo() throws DatabaseException {
		return this.findOne(0);
	}
	
	@Override
	public StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException {
		return this.findOne(2);
	}

}