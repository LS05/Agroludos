package agroludos.integration.dao.file.xml;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.system.HibernateConf;
import agroludos.utility.xml.XmlUtil;

class XmlDAOFactoryImpl implements XmlDAOFactory, FileDAOFactory{
	
	private static XmlConfigurazioneDAO xmlConf;
	
	XmlDAOFactoryImpl(HibernateConf sysConf, XmlUtil utXml){
		xmlConf = new XmlConfigurazioneDAO(sysConf, utXml);
	}
	
	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return xmlConf;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return null;
	}
	
}
