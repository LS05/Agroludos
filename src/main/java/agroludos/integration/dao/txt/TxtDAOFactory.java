package agroludos.integration.dao.txt;

import agroludos.integration.dao.CertificatoSRCDAO;
import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.FileDAOFactory;

public class TxtDAOFactory extends FileDAOFactory{

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO();
	}

	@Override
	public ConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

}