package agroludos.integration.dao.db.mysql;

import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class MySqlManagerDiSistemaDAO extends MySqlUtenteDAO implements ManagerDiSistemaDAO{

	@Override
	public boolean crea(ManagerDiSistemaTO mdsto) throws DatabaseException {
		return super.crea(mdsto);
	}

	@Override
	public <T> ManagerDiSistemaTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}
}