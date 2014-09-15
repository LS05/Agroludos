package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;

public interface OptionalDAO {

	boolean crea(OptionalTO optTO) throws DatabaseException;

	boolean update(OptionalTO optTO) throws DatabaseException;
	
	boolean eliminaOptional(OptionalTO optTO) throws DatabaseException;
	
	List<OptionalTO> readAll() throws DatabaseException;
	
	List<OptionalTO> readByTipo(OptionalTO optTO) throws DatabaseException;
}
