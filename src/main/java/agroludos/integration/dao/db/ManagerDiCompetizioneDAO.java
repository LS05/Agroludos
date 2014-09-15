package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface ManagerDiCompetizioneDAO {

	boolean crea(ManagerDiCompetizioneTO mdcto) throws DatabaseException;

	boolean update(ManagerDiCompetizioneTO mdcto) throws DatabaseException;

	boolean delete(ManagerDiCompetizioneTO mdcto);

	ManagerDiCompetizioneTO getByUsername(String username) throws DatabaseException;

	ManagerDiCompetizioneTO readByID(Integer id) throws DatabaseException;

	List<ManagerDiCompetizioneTO> readAll();
}