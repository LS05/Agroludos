package agroludos.business.as.gestorestatocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoCompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad uno Stato di una Competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LStatoCompetizione extends AgroludosService{

	/**
	 * 
	 * @return La lista di tutti gli stati di una competizione
	 * @throws DatabaseException
	 */
	List<StatoCompetizioneTO> getAllStatoCompetizione() throws DatabaseException;

	/**
	 * 
	 * @return {@link StatoCompetizioneTO} corrispondente ad una competizione annullata
	 * @throws DatabaseException
	 */
	StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException;

}
