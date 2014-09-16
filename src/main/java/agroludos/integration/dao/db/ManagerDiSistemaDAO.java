package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO {
	
	public boolean crea(ManagerDiSistemaTO mdsTO) throws DatabaseException;
	
	public ManagerDiSistemaTO getByUsername(String username) throws DatabaseException;
	
}
