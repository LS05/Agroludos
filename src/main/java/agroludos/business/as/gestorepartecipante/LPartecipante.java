package agroludos.business.as.gestorepartecipante;

import java.io.IOException;
import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.UserNotFoundException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un Partecipante
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LPartecipante extends AgroludosService {

	/**
	 * 
	 * @param parto
	 * @return {@link PartecipanteTO}
	 * @throws DatabaseException
	 * @throws IOException
	 * @throws UserNotFoundException
	 */
	PartecipanteTO getPartecipante(PartecipanteTO parto) throws DatabaseException, IOException, UserNotFoundException;

	/**
	 * 
	 * @return Lista di tutti i partecipanti
	 * @throws DatabaseException
	 * @throws IOException
	 */
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException, IOException;

	/**
	 * 
	 * @param parto
	 * @return il certificato appartenente al partecipante
	 * @throws DatabaseException
	 * @throws IOException
	 */
	CertificatoTO getCertificatoSRC(PartecipanteTO parto)throws DatabaseException, IOException;

	/**
	 * 
	 * @param parTO
	 * @return true se il certificado è valido false altrimenti
	 */
	boolean isCertificatoValido(PartecipanteTO parTO);

}