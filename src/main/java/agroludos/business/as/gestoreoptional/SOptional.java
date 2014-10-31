package agroludos.business.as.gestoreoptional;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.to.OptionalTO;

public interface SOptional extends AgroludosService{
	
	OptionalTO inserisciOptional(OptionalTO optto) throws DatabaseException, ValidationException;
	
	OptionalTO modificaOptional(OptionalTO optto) throws DatabaseException, ValidationException;
	
	OptionalTO disattivaOptional(OptionalTO optto) throws DatabaseException, ValidationException;

}
