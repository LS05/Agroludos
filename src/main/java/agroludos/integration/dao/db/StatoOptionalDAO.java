package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoOptionalTO;

public interface StatoOptionalDAO extends DAO<StatoOptionalTO> {

	StatoOptionalTO getStatoDisattivo() throws DatabaseException;

	StatoOptionalTO getStatoAttivo() throws DatabaseException;

}