package agroludos.business.as.gestoretipoutente;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoUtenteTO;

public interface LTipoUtente extends AgroludosService{
	public List<TipoUtenteTO> getAllTipoUtente() throws DatabaseException;
}
