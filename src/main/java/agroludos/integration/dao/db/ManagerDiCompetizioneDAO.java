package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.ManagerDiCompetizioneTO;

public interface ManagerDiCompetizioneDAO {
	
	boolean crea(ManagerDiCompetizioneTO mdcto);
	
	boolean update(ManagerDiCompetizioneTO mdcto);
	
	<T> ManagerDiCompetizioneTO readByUsername(T username);
	
	<T> ManagerDiCompetizioneTO readByID(T id);
	
	List<ManagerDiCompetizioneTO> readAll();

	boolean delete(ManagerDiCompetizioneTO mdcto);
}
