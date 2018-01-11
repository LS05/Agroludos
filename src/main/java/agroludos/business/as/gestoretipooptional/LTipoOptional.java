package agroludos.business.as.gestoretipooptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoOptionalTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un tipo optional
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LTipoOptional extends AgroludosService{
	/**
	 * 
	 * @return tutti i tipi di optional
	 * @throws DatabaseException
	 * @see {@link TipoOptionalTO}
	 */
	List<TipoOptionalTO> getAllTipoOptional() throws DatabaseException;
}
