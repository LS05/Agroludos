package agroludos.integration.dao.file;

import agroludos.integration.dao.file.txt.TxtDAOFactory;
import agroludos.integration.dao.file.xml.XmlDAOFactory;

class FileFactoryImpl implements FileFactory{

	private static XmlDAOFactory xmlFact;
	private static TxtDAOFactory txtFact;

	FileFactoryImpl(XmlDAOFactory xmlDaoFact, TxtDAOFactory txtDaoFact){
		xmlFact = xmlDaoFact;
		txtFact = txtDaoFact;
	}

	@Override
	public FileDAOFactory getDAOFactory(String tipo){
		FileDAOFactory res = null;

		if( "xml".equalsIgnoreCase(tipo) ){
			res = xmlFact;
		} else if( "txt".equals(tipo) ){
			res = txtFact;
		}

		return res;
	}
}