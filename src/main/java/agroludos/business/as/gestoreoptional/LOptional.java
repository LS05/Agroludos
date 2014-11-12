package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.OptionalCmpAttiveException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.TipoOptionalTO;

public interface LOptional extends AgroludosService{
	
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException;
	
	List<OptionalTO> getAllOptional() throws DatabaseException;
	
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
	
	List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException;

	OptionalTO checkOptCmpAttive(OptionalTO optto) throws DatabaseException,
			OptionalCmpAttiveException;

	List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException;
	
}