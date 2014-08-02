package agroludos.integration.dao.config;

public abstract class ConfigurazioneDAOFactory {
	private static ConfigurazioneDAO confDAO;
	
	public static ConfigurazioneDAOFactory getDAOFactory(){
		confDAO = ConfigurazioneDAO.getInstance();
		return confDAO.getDAOFactory();
	}
	
	public abstract FConfigurazioneDAO getConfigurazioneDAO();

}
