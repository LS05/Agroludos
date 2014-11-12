package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.DatabaseTO;

public interface SConfigurazione extends AgroludosService {

	boolean inserisciConfigurazione(DatabaseTO dbto) throws DatabaseException, ValidationException;

}