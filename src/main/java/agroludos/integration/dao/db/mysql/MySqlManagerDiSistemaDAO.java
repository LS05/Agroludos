package agroludos.integration.dao.db.mysql;

import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.UtenteTO;

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
	public ManagerDiSistemaTO read(UtenteTO uto) {
		ManagerDiSistemaTO res = null;
		this.session.beginTransaction();

		this.session.getTransaction().commit();
		return res;
	}
}