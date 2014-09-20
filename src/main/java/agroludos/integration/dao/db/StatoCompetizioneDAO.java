package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.StatoCompetizioneTO;

public interface StatoCompetizioneDAO extends DAO<StatoCompetizioneTO>{

	StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException;

}
