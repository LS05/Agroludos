package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.ManagerDiCompetizioneTO;

public interface ManagerDiCompetizioneDAO extends UtenteDAO<ManagerDiCompetizioneTO>{

	List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException;

}