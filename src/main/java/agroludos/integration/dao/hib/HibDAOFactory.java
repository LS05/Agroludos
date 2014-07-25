package agroludos.integration.dao.hib;

import java.sql.Connection;
import java.sql.DriverManager;


import agroludos.integration.dao.CompetizioneDAO;
import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.integration.dao.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.ManagerDiSistemaDAO;
import agroludos.integration.dao.OptionalDAO;
import agroludos.integration.dao.PartecipanteDAO;
import agroludos.integration.dao.TipoCompetizioneDAO;
import agroludos.integration.dao.TipoOptionalDAO;
import agroludos.integration.dao.UtenteDAO;

public class HibDAOFactory extends DAOFactory {

	/**
	 * Name of the class that holds the jdbc driver implementation for the MySQL database
	 */
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * URI of the database to connect to
	 */
	public static final String DBURL = "jdbc:mysql://";

	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";

	// --------------------------------------------

	static {
		try {
			Class.forName(DRIVER).newInstance();
		} 
		catch (Exception e) {
			System.err.println("MySqlDAOFactory.class: failed to load MySQL JDBC driver\n"+e);
			e.printStackTrace();
		}
	}

	// --------------------------------------------
	
	public static Connection createConnection() {
		try {
			Connection conn = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
			System.out.println(HibDAOFactory.class.getName()+".createConnection(): database connection established");
			return conn;
		} 
		catch (Exception e) {
			System.err.println(HibDAOFactory.class.getName()+".createConnection(): failed creating connection\n"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(HibDAOFactory.class.getName()+".closeConnection(): failed closing connection\n"+e);
			e.printStackTrace();
		}
	}

	@Override
	public ManagerDiCompetizioneDAO getManagerDiCompetizioneDAO() {
		return new HibManagerDiCompetizioneDAO();
	}

	@Override
	public ManagerDiSistemaDAO getManagerDiSistemaDAO() {
		return new HibManagerDiSistemaDAO();
	}

	@Override
	public CompetizioneDAO getCompetizioneDAO() {
		return new HibCompetizioneDAO();
	}

	@Override
	public PartecipanteDAO getPartecipanteDAO() {
		return new HibPartecipanteDAO();
	}

	@Override
	public TipoCompetizioneDAO getTipoCompetizioneDAO() {
		return new HibTipoCompetizioneDAO();
	}

	@Override
	public TipoOptionalDAO getTipoOptionalDAO() {
		return new HibTipoOptionalDAO();
	}

	@Override
	public OptionalDAO getOptionalDAO() {
		return new HibOptionalDAO();
	}

	@Override
	public UtenteDAO getUtenteDAO() {
		return new HibUtenteDAO();
	}

	@Override
	public ConfigurazioneDAO getConfigurazioneDAO() {
		return new HibConfigurazioneDAO();
	}
}