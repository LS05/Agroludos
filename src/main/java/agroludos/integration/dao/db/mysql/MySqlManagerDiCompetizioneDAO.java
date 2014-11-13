package agroludos.integration.dao.db.mysql;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.ManagerDiCompetizioneDAO;
import agroludos.to.ManagerDiCompetizioneTO;

class MySqlManagerDiCompetizioneDAO extends MySqlUtenteDAO<ManagerDiCompetizioneTO> implements ManagerDiCompetizioneDAO{

	MySqlManagerDiCompetizioneDAO(){
		this.setClasse(toFact.createMdCTO());
	}

	@Override
	public List< ManagerDiCompetizioneTO > getAll() throws DatabaseException{
		List<ManagerDiCompetizioneTO> res = super.getAll();

		return res;
	}

	@Override
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione() throws DatabaseException {
		return super.executeQuery("getAllManagerDiCompetizione");
	}

}