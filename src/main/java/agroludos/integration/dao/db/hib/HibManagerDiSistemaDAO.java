package agroludos.integration.dao.db.hib;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class HibManagerDiSistemaDAO extends HibUtenteDAO<ManagerDiSistemaTO> implements ManagerDiSistemaDAO{

	HibManagerDiSistemaDAO(){
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