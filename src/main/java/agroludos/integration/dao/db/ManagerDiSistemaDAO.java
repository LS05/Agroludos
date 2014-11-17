package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda il manager di sistema.
 * Sono presenti i metodi di lettura.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface ManagerDiSistemaDAO extends UtenteDAO<ManagerDiSistemaTO>{
	
	/**
	 * 
	 * @return vero se esiste nella sorgente dati il manager di sistema, falso altrimenti
	 * @throws DatabaseException
	 */
	public boolean checkMds() throws DatabaseException;
	
}