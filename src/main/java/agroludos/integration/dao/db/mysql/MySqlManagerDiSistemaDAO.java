package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.Session;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class MySqlManagerDiSistemaDAO extends MySqlUtenteDAO<ManagerDiSistemaTO> implements ManagerDiSistemaDAO{

	MySqlManagerDiSistemaDAO(Session session){
		super(session);
		this.setClasse(ManagerDiSistemaTO.class);
	}

	@Override
	public ManagerDiSistemaTO getByTelefono(String telefono) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
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