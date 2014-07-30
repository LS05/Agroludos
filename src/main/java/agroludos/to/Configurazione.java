package agroludos.to;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

class Configurazione implements ConfigurazioneTO{
	private String pathConf;
	private int idConf;
	private boolean statoConf;
	
	public boolean getStatoConf() {
		return statoConf;
	}

	public void setStatoConf(boolean statoConf) {
		this.statoConf = statoConf;
	}
	
	public String getPathConf() {
		return pathConf;
	}

	public void setPathConf(String pathConf) {
		this.pathConf = pathConf;
	}

	public int getIdConf() {
		return idConf;
	}

	public void setIdConf(int idConf) {
		this.idConf = idConf;
	}
	
	
	public static void main(String args[]){
		ConfigurazioneTO conf = new Configurazione();
		conf.setPathConf("test");
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(conf);
		session.getTransaction().commit();
	}
}
