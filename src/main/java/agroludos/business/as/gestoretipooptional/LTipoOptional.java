package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoOptionalTO;


public interface LTipoOptional {
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
}
