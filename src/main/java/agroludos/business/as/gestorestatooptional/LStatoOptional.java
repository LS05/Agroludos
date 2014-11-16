package agroludos.business.as.gestorestatooptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoOptionalTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili agli stati degli optional
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see agroludos.to.StatoOptionalTO
 * @see agroludos.business.as.AgroludosService
 */
public interface LStatoOptional extends AgroludosService{
	
	/**
	 * Il metodo restituisce una lista di tutti gli Stati di un optional presenti nella sorgente dati
	 * Utilizza il DAO StatoOptionalDAO
	 * @return List di StatoOptionalTO
	 * @throws DatabaseException
	 * @see agroludos.integration.dao.db.StatoOptionalDAO
	 */
	List<StatoOptionalTO> getAllStatoOptional() throws DatabaseException;

}
