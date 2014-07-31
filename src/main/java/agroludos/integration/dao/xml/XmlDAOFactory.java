package agroludos.integration.dao.xml;

import agroludos.integration.dao.CertificatoSRCDAO;
import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.FileDAOFactory;

public class XmlDAOFactory extends FileDAOFactory {

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return null;
	}

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return new XmlConfigurazioneDAO();
	}

}
