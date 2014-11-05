package agroludos.integration.dao.file;

import agroludos.integration.dao.file.txt.TxtDAOFactory;
import agroludos.integration.dao.file.xml.XmlDAOFactory;
import agroludos.system.Conf;
import agroludos.to.TOFactory;

class FileFactoryImpl implements FileFactory{

	private static XmlDAOFactory xmlFact;
	private static TxtDAOFactory txtFact;
	private TOFactory toFact;
	private Conf sysConf;

	FileFactoryImpl(TOFactory toFact, Conf sysConf){
		this.toFact = toFact;
		this.sysConf = sysConf;
		xmlFact = new XmlDAOFactory();
		txtFact = new TxtDAOFactory(this.toFact, this.sysConf);
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