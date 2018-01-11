package agroludos.business.as.gestoretipocompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ai tipi di competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface STipoCompetizione extends AgroludosService{
	/**
	 * 
	 * @param tcmto
	 * @return il tipo di competizione inserito
	 * @throws DatabaseException
	 * @throws ValidationException
	 * @see TipoCompetizioneTO
	 */
	TipoCompetizioneTO inserisciTipoCompetizione(TipoCompetizioneTO tcmto) throws DatabaseException, ValidationException;
}
