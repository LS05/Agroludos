package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ai tipi di competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LTipoCompetizione extends AgroludosService{
	
	/**
	 * 
	 * @return tutti i tipi di competizione
	 * @throws DatabaseException
	 * @see {@link TipoCompetizioneTO}
	 */
	List<TipoCompetizioneTO> getAllTipoCompetizione() throws DatabaseException;
}
