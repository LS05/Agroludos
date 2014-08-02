package agroludos.integration.dao.mysql;

import org.hibernate.Session;

public abstract class MySqlDAO {

	protected Session session;
	
	MySqlDAO(){
		this.session = MySqlDAOFactory.getSession();
	}

}
