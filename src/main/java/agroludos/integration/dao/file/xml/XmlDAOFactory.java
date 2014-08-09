package agroludos.integration.dao.file.xml;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;

public class XmlDAOFactory implements FileDAOFactory{
	
	private static XmlConfigurazioneDAO xmlConf = new XmlConfigurazioneDAO();
	
	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return xmlConf;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return null;
	}
	
}
