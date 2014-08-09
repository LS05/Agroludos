package agroludos.integration.dao.file;

import agroludos.integration.dao.txt.TxtDAOFactory;
import agroludos.integration.dao.xml.XmlDAOFactory;

public class FileDAOFactory {
	private static XmlDAOFactory xmlFact = new XmlDAOFactory();
	private static TxtDAOFactory txtFact = new TxtDAOFactory();
	
	public FileDAO getDAOFactory(String tipo){
		FileDAO res = null;
		if(tipo.equals("xml"))
			res = xmlFact;
		else if(tipo.equals("txt"))
			res = txtFact;
		return res;
	}
}
