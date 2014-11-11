package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoUtenteTO;

public interface TipoUtenteDAO extends DAO<TipoUtenteTO> {

	TipoUtenteTO getTipoUtenteMds() throws DatabaseException;
	
	TipoUtenteTO getTipoUtenteMdc() throws DatabaseException;
	
	TipoUtenteTO getTipoUtentePart() throws DatabaseException;
	
}
