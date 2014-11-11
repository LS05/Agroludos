package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.StatoUtenteTO;

public interface StatoUtenteDAO extends DAO<StatoUtenteTO> {
	
	StatoUtenteTO getStatoAttivo() throws DatabaseException;
	
	StatoUtenteTO getStatoDisattivo() throws DatabaseException;
}
