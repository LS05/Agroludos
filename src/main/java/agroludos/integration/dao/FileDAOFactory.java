package agroludos.integration.dao;

import agroludos.integration.dao.txt.TxtDAOFactory;

abstract public class FileDAOFactory {
	
	public static FileDAOFactory getDAOFactory(String tipoFile) {
		if(tipoFile.equals("txt"))
			return new TxtDAOFactory();
		else
			return null;
	}
	
	public abstract FileDAO getCertificatoSRCDAO();
}
