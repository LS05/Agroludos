package agroludos.integration.dao.db;

import agroludos.to.ManagerDiSistemaTO;

public interface ManagerDiSistemaDAO {
	boolean crea(ManagerDiSistemaTO mdsto);
	<T> ManagerDiSistemaTO readByUsername(T username);
}
