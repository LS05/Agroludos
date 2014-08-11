package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.UtenteTO;

public class MySqlManagerDiSistemaDAO extends MySqlDAO implements ManagerDiSistemaDAO{
	
	MySqlManagerDiSistemaDAO(){
		super();
	}
	
	@Override
	public boolean crea(UtenteTO uto) {
		// TODO Auto-generated method stub
		return false;
	}

}
