package agroludos.integration.dao.db;


import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

public interface OptionalDAO extends DAO<OptionalTO>{

	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException;

	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException;

	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException;

}
