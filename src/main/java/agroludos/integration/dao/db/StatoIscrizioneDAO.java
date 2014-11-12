package agroludos.integration.dao.db;


import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoIscrizioneTO;

public interface StatoIscrizioneDAO extends DAO<StatoIscrizioneTO> {

	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
	
	StatoIscrizioneTO getStatoDisattivo() throws DatabaseException;

	StatoIscrizioneTO getStatoIscrizioneTerminato() throws DatabaseException;

}
