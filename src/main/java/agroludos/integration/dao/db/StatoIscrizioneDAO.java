package agroludos.integration.dao.db;


import agroludos.exceptions.DatabaseException;
import agroludos.to.StatoIscrizioneTO;

public interface StatoIscrizioneDAO extends DAO<StatoIscrizioneTO> {

	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;

}
