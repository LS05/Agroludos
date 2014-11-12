package agroludos.business.as.gestorestatoutente;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoUtenteTO;

public interface LStatoUtente extends AgroludosService{
	List<StatoUtenteTO> getAllStatoUtente() throws DatabaseException;
}
