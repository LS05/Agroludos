package agroludos.business.as.gestorecompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CompetizioneTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad una competizione
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.business.as.AgroludosService
 */
public interface SCompetizione extends AgroludosService {
	
	/**
	 * Il metodo inserisce una Competizione nella sorgente dati
	 * utilizzando il DAO CompetizioneDAO
	 * 
	 * @param cmpto
	 * @return CompetizioneTO
	 * @throws DatabaseException
	 * @throws ValidationException 
	 * @see agroludos.to.CompetizioneTO
	 * @see agroludos.integration.dao.db.CompetizioneDAO
	 */
	CompetizioneTO inserisciCompetizione(CompetizioneTO cmpto) throws DatabaseException, ValidationException;
	
	/**
	 * Il metodo modifica una Competizione nella sorgente dati utilizzando il DAO CompetizioneDAO 
	 * 
	 * <br><b>Nel dettaglio:</b>
	 * Il metodo modifica la competizione dopo aver controllato che siano rispettati i vincoli
	 * e che abbia passato la validazione dei campi.
	 * Dopo aver modificato la competizione controlla che non ci siano iscrizioni in esubeto, se ci 
	 * sono elimina tali iscrizioni utilizzando il metodo {@link #eliminaIscrizioniInEsubero}.
	 * Infine invia una email a tutti gli iscritti alla competizione per notificare le modifiche e
	 * restituisce la competizione modificata.
	 * 
	 * @param cmpto
	 * @return CompetizioneTO
	 * @throws DatabaseException
	 * @throws ValidationException 
	 * @see agroludos.to.CompetizioneTO
	 * @see agroludos.integration.dao.db.CompetizioneDAO
	 */
	CompetizioneTO modificaCompetizione(CompetizioneTO cmpto) throws DatabaseException, ValidationException;
	
	/**
	 * Il metodo annulla una Competizione nella sorgente dati utilizzando il DAO CompetizioneDAO 
	 * Dopodichè invia una email a tutti gli iscritti
	 * 
	 * @param cmpto
	 * @return
	 * @throws DatabaseException
	 * @see agroludos.to.CompetizioneTO
	 * @see agroludos.integration.dao.db.CompetizioneDAO
	 * @see agroludos.to.EmailTO
	 */
	CompetizioneTO annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
}
