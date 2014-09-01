package agroludos.business.as.gestoretipooptional;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoOptionalTO;


public interface STipoOptional {
	boolean inserisciTipoOptional(TipoOptionalTO topto) throws DatabaseException;
}
