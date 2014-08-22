package agroludos.integration.dao.db.mysql;

import org.hibernate.Session;

class MySqlAgroludosDAO {
	protected Session session;
	
	MySqlAgroludosDAO(){
		this.session = MySqlDAO.getSessionFactory().openSession();
	}
	
}
