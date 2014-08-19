package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

import agroludos.integration.dao.db.ManagerDiSistemaDAO;
import agroludos.to.ManagerDiSistemaTO;

public class MySqlManagerDiSistemaDAO implements ManagerDiSistemaDAO{
	private Session session;
	
	MySqlManagerDiSistemaDAO(){
		this.session = MySqlDAO.getSessionFactory().openSession();
	}
	
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

}
