package agroludos.integration.dao.db.mysql;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class MySqlManagerDiSistemaDAO extends MySqlUtenteDAO<ManagerDiSistemaTO> implements ManagerDiSistemaDAO{

	MySqlManagerDiSistemaDAO(){
		this.setClasse(toFact.createMdSTO());
	}

	@Override
	public boolean checkMds() throws DatabaseException {
		boolean res;
		List<ManagerDiSistemaTO> list = super.executeQuery("checkMds");

		if(list.size() != 0){
			res = true;
		}else{
			res = false;
		}

		return res;
	}
}