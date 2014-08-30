package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.UtenteTO;

public interface ManagerDiCompetizioneDAO {
	
	boolean crea(ManagerDiCompetizioneTO mdcto);
	
	boolean update(ManagerDiCompetizioneTO mdcto);
	
	ManagerDiCompetizioneTO read(UtenteTO uto);
	
	<T> ManagerDiCompetizioneTO readByUsername(T username);
	
	<T> ManagerDiCompetizioneTO readByID(T id);
	
	List<ManagerDiCompetizioneTO> readAll();

}
