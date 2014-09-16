package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface ManagerDiCompetizioneDAO extends UtenteDAO<ManagerDiCompetizioneTO>{

	ManagerDiCompetizioneTO getByStipendio(Integer id) throws DatabaseException;

}