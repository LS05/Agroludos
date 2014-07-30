package agroludos.integration.dao.mysql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import agroludos.integration.dao.CompetizioneDAO;
import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.integration.dao.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.ManagerDiSistemaDAO;
import agroludos.integration.dao.OptionalDAO;
import agroludos.integration.dao.PartecipanteDAO;
import agroludos.integration.dao.TipoCompetizioneDAO;
import agroludos.integration.dao.TipoOptionalDAO;
import agroludos.integration.dao.UtenteDAO;

public class MySqlDAOFactory extends DAOFactory {
	
	private static Session session;
	private static SessionFactory sessionFactory; 
	// --------------------------------------------
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}

	// --------------------------------------------

	public static Session getSession() {
		return session;
	}

	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new MySqlManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new MySqlManagerDiSistemaDAO();
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new MySqlCompetizioneDAO();
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new MySqlPartecipanteDAO();
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new MySqlTipoCompetizioneDAO();
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new MySqlTipoOptionalDAO();
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new MySqlOptionalDAO();
	}

	@Override
	public UtenteDAO getUtenteDAO() {
		return new MySqlUtenteDAO();
	}

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return new MySqlConfigurazioneDAO();
	}
}