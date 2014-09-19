package agroludos.business.as.gestoreoptional;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;

public interface SOptional extends AgroludosService{
	
	boolean inserisciOptional(OptionalTO optto) throws DatabaseException;
	
	OptionalTO modificaOptional(OptionalTO optto) throws DatabaseException;
	
	OptionalTO disattivaOptional(OptionalTO optto) throws DatabaseException;

}
