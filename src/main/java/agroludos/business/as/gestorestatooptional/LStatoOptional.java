package agroludos.business.as.gestorestatooptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoOptionalTO;

public interface LStatoOptional extends AgroludosService{
	List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException;

}
