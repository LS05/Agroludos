package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;


public interface LOptional extends AgroludosService{
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO optto) throws DatabaseException;
	List<OptionalTO> getAllOptional() throws DatabaseException;
	List<TipoOptionalTO> getTipoOptionalByMds()  throws DatabaseException;
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
}
