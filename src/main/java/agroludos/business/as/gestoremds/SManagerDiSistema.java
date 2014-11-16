package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

/**
 * L'interfaccia rappresenta i servizi di scrittura applicabili ad un Manager Di Sistema
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface SManagerDiSistema extends AgroludosService{
	/**
	 * 
	 * @param mdsto
	 * @return {@link ManagerDiSistemaTO}
	 * @throws DatabaseException
	 * @throws ValidationException
	 */
	ManagerDiSistemaTO nuovoManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException, ValidationException;
}
