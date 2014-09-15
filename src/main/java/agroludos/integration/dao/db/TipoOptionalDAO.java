package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoOptionalTO;

public interface TipoOptionalDAO {

	boolean crea(TipoOptionalTO topto) throws DatabaseException;

	List<TipoOptionalTO> readAll() throws DatabaseException;
	
}