package agroludos.integration.dao.db.hib;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import agroludos.exceptions.system.DatabaseException;

class HibDAOUtil{

	private static SessionFactory sessionFactory;

	public static SessionFactory buildSessionFactory() throws DatabaseException{
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

	public static SessionFactory getSessionFactory(){
		return sessionFactory;		
	}
}