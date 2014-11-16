package agroludos.business.as.gestoremds;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.MdsNotFoundException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un Manager Di Sistema
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LManagerDiSistema  extends AgroludosService{
	/**
	 * 
	 * @param mdsto
	 * @return {@link ManagerDiSistemaTO}
	 * @throws DatabaseException
	 */
	ManagerDiSistemaTO getManagerDiSistema(ManagerDiSistemaTO mdsto) throws DatabaseException;
	
	/**
	 * 
	 * @return boolean
	 * @throws DatabaseException
	 * @throws MdsNotFoundException
	 */
	public boolean checkMds() throws DatabaseException, MdsNotFoundException;
}
