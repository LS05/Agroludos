package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO extends UtenteDAO<ManagerDiSistemaTO>{
	
	public boolean checkMds() throws DatabaseException;
	
}