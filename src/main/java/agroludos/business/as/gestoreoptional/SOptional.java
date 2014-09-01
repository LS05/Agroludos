package agroludos.business.as.gestoreoptional;

import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;

public interface SOptional {
	boolean inserisciOptional(OptionalTO optto) throws DatabaseException;
	boolean modificaOptional(OptionalTO optto) throws DatabaseException;
	boolean eliminaOptional(OptionalTO optto) throws DatabaseException;
}
