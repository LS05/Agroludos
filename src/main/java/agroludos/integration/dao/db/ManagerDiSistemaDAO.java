package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO {
	boolean crea(ManagerDiSistemaTO mdsTO) throws DatabaseException;
	public <T> ManagerDiSistemaTO readByUsername(T username);
}
