package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO {
	boolean crea(ManagerDiSistemaTO mdsTO) throws DatabaseException;
	public ManagerDiSistemaTO readByUsername(String username) throws DatabaseException;
}
