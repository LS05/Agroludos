package agroludos.business.as.gestoreconfigurazione;

import agroludos.exceptions.DatabaseException;
import agroludos.to.DatabaseTO;

public interface SConfigurazione {
	boolean inserisciConfigurazione(DatabaseTO dbto) throws DatabaseException;
}
