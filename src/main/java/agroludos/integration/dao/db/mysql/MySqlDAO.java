package agroludos.integration.dao.db.mysql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public abstract class MySqlDAO {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		SessionFactory res = configuration.buildSessionFactory(serviceRegistry);
		return res;
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}