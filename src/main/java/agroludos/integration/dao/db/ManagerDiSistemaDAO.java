package agroludos.integration.dao.db;

import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

public interface ManagerDiSistemaDAO {
	boolean crea(ManagerDiSistemaTO mdsto);
	ManagerDiSistemaTO read(UtenteTO uto);
}
