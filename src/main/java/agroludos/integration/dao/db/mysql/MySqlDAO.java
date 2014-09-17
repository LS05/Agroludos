package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import agroludos.exceptions.DatabaseException;

class MySqlDAO implements MySqlDAOUtil{

	private static SessionFactory sessionFactory;
	
	@Override
	public SessionFactory buildSessionFactory() throws DatabaseException{
		SessionFactory res = null;

		if(sessionFactory == null){
			Configuration configuration = new Configuration();
			configuration.configure();
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
					configuration.getProperties()).build();
			try{
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				res = sessionFactory;
			}catch(Exception e){
				throw new DatabaseException(e.getMessage());
			}
		} else {
			res = sessionFactory;
		}

		return res;
	}
	
	@Override
	public SessionFactory getSessionFactory(){
		return sessionFactory;		
	}
}