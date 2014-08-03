package agroludos.integration.dao.file;

public abstract class FileDAOFactory {
	private static FileDAO confDAO;
	
	public static FileDAOFactory getDAOFactory(String tipo){
		confDAO = FileDAO.getFileDAO(tipo);
		return confDAO.getDAOFactory();
	}
	
	public abstract FConfigurazioneDAO getConfigurazioneDAO();

}
