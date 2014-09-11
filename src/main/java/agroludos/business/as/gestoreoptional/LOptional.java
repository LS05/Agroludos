package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;


public interface LOptional extends AgroludosService{
	List<OptionalTO> getOptionalByTipo(OptionalTO optto) throws DatabaseException;
	List<OptionalTO> getAllOptional() throws DatabaseException;

}
