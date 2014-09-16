package agroludos.integration.dao.db.mysql;

import java.util.List;

import org.hibernate.SessionFactory;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

class MySqlManagerDiSistemaDAO extends MySqlUtenteDAO implements ManagerDiSistemaDAO{

	MySqlManagerDiSistemaDAO(SessionFactory sessionFactory){
		super(sessionFactory);
	}
	
	@Override
	public boolean crea(ManagerDiSistemaTO mdsto) throws DatabaseException {
		return super.crea(mdsto);
	}

	@Override
	public ManagerDiSistemaTO getByUsername(String username) throws DatabaseException {
		return (ManagerDiSistemaTO) super.getByUsername(username);
	}

	@Override
	public boolean checkMds() throws DatabaseException {
		boolean res;
		List<UtenteTO> list = super.executeQuery("checkMds");

		if(list.size() != 0){
			res = true;
		}else{
			res = false;
		}
		
		return res;
	}
}