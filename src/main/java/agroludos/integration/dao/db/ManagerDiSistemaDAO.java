package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO extends UtenteDAO{

	public ManagerDiSistemaTO getByTelefono(String telefono) throws DatabaseException;
	
	public boolean checkMds() throws DatabaseException;
	
}