package agroludos.integration.dao.file;

import agroludos.integration.dao.file.txt.TxtDAOFactory;
import agroludos.integration.dao.file.xml.XmlDAOFactory;

public class FileFactory {
	private static XmlDAOFactory xmlFact = new XmlDAOFactory();
	private static TxtDAOFactory txtFact = new TxtDAOFactory();
	
	public FileDAOFactory getDAOFactory(String tipo){
		FileDAOFactory res = null;
		
		if(tipo.toLowerCase().equals("xml"))
			res = xmlFact;
		else if(tipo.equals("txt"))
			res = txtFact;
		
		return res;
	}
}
