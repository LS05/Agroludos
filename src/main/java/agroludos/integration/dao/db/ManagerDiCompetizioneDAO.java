package agroludos.integration.dao.db;

import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.UtenteTO;

public interface ManagerDiCompetizioneDAO {
	boolean crea(ManagerDiCompetizioneTO mdcto);
	ManagerDiCompetizioneTO read(UtenteTO uto);
}
