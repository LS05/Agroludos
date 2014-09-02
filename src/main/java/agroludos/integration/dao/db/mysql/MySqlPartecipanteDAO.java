package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.PartecipanteDAO;
import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

class MySqlPartecipanteDAO implements PartecipanteDAO {

	@Override
	public boolean crea(PartecipanteTO mdcto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PartecipanteTO read(UtenteTO uto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> PartecipanteTO readByUsername(T username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> PartecipanteTO readByID(T id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(PartecipanteTO parto) {
		// TODO Auto-generated method stub
		return false;
	}
}