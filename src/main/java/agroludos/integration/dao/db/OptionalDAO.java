package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.OptionalTO;

public interface OptionalDAO {

	boolean crea(OptionalTO optto);

	boolean update(OptionalTO optto);
	
	boolean eliminaOptional(OptionalTO optto);
	
	List<OptionalTO> readAll();
	
	List<OptionalTO> readByTipo(Integer tipo);
}
