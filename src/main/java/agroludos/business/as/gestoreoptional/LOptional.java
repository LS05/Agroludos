package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.OptionalCmpAttiveException;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.TipoOptionalTO;

public interface LOptional extends AgroludosService{
	
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO optto) throws DatabaseException;
	
	List<OptionalTO> getAllOptional() throws DatabaseException;
	
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
	
	List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException;

	OptionalTO checkOptCmpAttive(OptionalTO optto) throws DatabaseException,
			OptionalCmpAttiveException;
	
}