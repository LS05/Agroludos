package agroludos.business.as.gestorepartecipante;

import java.io.IOException;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.PartecipanteTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad un Partecipante
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SPartecipante extends AgroludosService{

	/**
	 * 
	 * @param parto
	 * @return {@link PartecipanteTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 * @throws IOException
	 */
	PartecipanteTO inserisciPartecipante(PartecipanteTO parto) throws DatabaseException, ValidationException, IOException;

	/**
	 * 
	 * @param parto
	 * @return {@link PartecipanteTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 * @throws IOException
	 */
	PartecipanteTO modificaPartecipante (PartecipanteTO parto) throws DatabaseException, ValidationException, IOException;

}
