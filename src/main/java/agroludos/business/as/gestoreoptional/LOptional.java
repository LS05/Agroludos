package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;


public interface LOptional extends AgroludosService{
	<T> List<OptionalTO> getOptionalByTipo(T tipo) throws DatabaseException;
	List<OptionalTO> getAllOptional() throws DatabaseException;

}
