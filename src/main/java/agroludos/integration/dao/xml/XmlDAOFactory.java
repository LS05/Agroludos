package agroludos.integration.dao.xml;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAO;

public class XmlDAOFactory implements FileDAO{
	
	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return new XmlConfigurazioneDAO();
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return null;
	}
	
}
