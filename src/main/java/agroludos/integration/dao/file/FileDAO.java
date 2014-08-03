package agroludos.integration.dao.file;

import agroludos.factory.Factory;
import agroludos.integration.dao.AgroludosDAO;
import agroludos.integration.dao.IntAgroludosDAO;

class FileDAO extends Factory{
	private static FileDAOFactory dao;
	private static FileDAO fileDAO;
	private static AgroludosDAO agDAO;
	
	private FileDAO(String tipo){ 
		super("file");
		agDAO = IntAgroludosDAO.getAgroludosDAOI();
		dao = (FileDAOFactory)this.getInstance(this.initData(tipo));
	}
	
	public static FileDAO getFileDAO(String tipo){
		if(fileDAO == null)
			fileDAO = new FileDAO(tipo);
		return fileDAO;
	}

	
	public static FileDAOFactory getDAOFactory(){
		return dao;
	}

	protected String getXMLPath(){
		return agDAO.getFilePath();
	}
}