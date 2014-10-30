package agroludos.business.as.gestoretipooptional;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.to.TipoOptionalTO;


public interface STipoOptional extends AgroludosService{
	TipoOptionalTO inserisciTipoOptional(TipoOptionalTO topto) throws DatabaseException, ValidationException;
}
