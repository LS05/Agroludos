package agroludos.business.as.gestorestatoiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoIscrizioneTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad uno stato di una iscrizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LStatoIscrizione extends AgroludosService{
	
	/**
	 * 
	 * @return tutti gli stati di una Iscrizione
	 * @throws DatabaseException
	 */
	List<StatoIscrizioneTO> getAllStatoIscrizione() throws DatabaseException;
	
	/**
	 * 
	 * @return {@link StatoIscrizioneTO} corrispondente ad una iscrizione attiva
	 * @throws DatabaseException
	 */
	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
}
