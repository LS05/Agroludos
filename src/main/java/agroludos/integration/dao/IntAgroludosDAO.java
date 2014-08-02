package agroludos.integration.dao;

public class IntAgroludosDAO {
	private static DAO agDAO = new DAO();

	public static AgroludosDAO getAgroludosDAOI(){
		return agDAO;
	}
}
