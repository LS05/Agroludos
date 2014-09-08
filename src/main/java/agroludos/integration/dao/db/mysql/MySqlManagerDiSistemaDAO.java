package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

class MySqlManagerDiSistemaDAO extends MySqlAgroludosDAO implements ManagerDiSistemaDAO{

	@Override
	public boolean crea(ManagerDiSistemaTO mdsto) {
		boolean res = false;
		// TODO Aggiungere gestione eccezioni hibernate
		this.session.beginTransaction();
		this.session.save(mdsto);
		res = true;
		this.session.getTransaction().commit();
		return res;
	}

	@Override
	public <T> ManagerDiSistemaTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}
}