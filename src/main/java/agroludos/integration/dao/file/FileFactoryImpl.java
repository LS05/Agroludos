package agroludos.integration.dao.file;

import agroludos.integration.dao.file.txt.TxtDAOFactory;
import agroludos.integration.dao.file.xml.XmlDAOFactory;
import agroludos.to.TOFactory;

class FileFactoryImpl implements FileFactory{
	
	private static XmlDAOFactory xmlFact;
	private static TxtDAOFactory txtFact;
	private TOFactory toFact;
	
	FileFactoryImpl(TOFactory toFact){
		this.toFact = toFact;
		xmlFact = new XmlDAOFactory();
		txtFact = new TxtDAOFactory(this.toFact);
	}

	@Override
	public FileDAOFactory getDAOFactory(String tipo){
		FileDAOFactory res = null;

		if(tipo.equalsIgnoreCase("xml")){
			res = xmlFact;
		} else if(tipo.equals("txt")){
			res = txtFact;
		}

		return res;
	}
}
