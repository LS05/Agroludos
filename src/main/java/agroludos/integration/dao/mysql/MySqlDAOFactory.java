package agroludos.integration.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;


import agroludos.integration.dao.CompetizioneDAO;
import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.integration.dao.FileDAO;
import agroludos.integration.dao.ManagerDiCompetizioneDAO;
import agroludos.integration.dao.ManagerDiSistemaDAO;
import agroludos.integration.dao.OptionalDAO;
import agroludos.integration.dao.PartecipanteDAO;
import agroludos.integration.dao.TipoCompetizioneDAO;
import agroludos.integration.dao.TipoOptionalDAO;
import agroludos.integration.dao.UtenteDAO;

public class MySqlDAOFactory extends DAOFactory {

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
			System.out.println(MySqlDAOFactory.class.getName()+".createConnection(): database connection established");
			return conn;
		} 
		catch (Exception e) {
			System.err.println(MySqlDAOFactory.class.getName()+".createConnection(): failed creating connection\n"+e);
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}
		catch (Exception e) {
			System.err.println(MySqlDAOFactory.class.getName()+".closeConnection(): failed closing connection\n"+e);
			e.printStackTrace();
		}
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