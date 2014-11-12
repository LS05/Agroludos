package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoOptionalTO;


public interface LTipoOptional extends AgroludosService{
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
}
