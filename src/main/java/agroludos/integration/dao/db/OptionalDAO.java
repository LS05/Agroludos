package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.OptionalTO;

public interface OptionalDAO {

	boolean crea(OptionalTO optTO);

	boolean update(OptionalTO optTO);
	
	boolean eliminaOptional(OptionalTO optTO);
	
	List<OptionalTO> readAll();
	
	List<OptionalTO> readByTipo(OptionalTO optTO);
}
