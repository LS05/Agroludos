package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO {

	public ManagerDiSistemaTO getByTelefono(String telefono) throws DatabaseException;

}