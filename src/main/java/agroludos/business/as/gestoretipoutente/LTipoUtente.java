package agroludos.business.as.gestoretipoutente;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoUtenteTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un tipo di utente
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LTipoUtente extends AgroludosService{
	/**
	 * 
	 * @return tutti i tipi di utente
	 * @throws DatabaseException
	 * @see {@link TipoUtenteTO}
	 */
	public List<TipoUtenteTO> getAllTipoUtente() throws DatabaseException;
}
