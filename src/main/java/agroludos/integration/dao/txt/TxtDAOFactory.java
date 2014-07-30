package agroludos.integration.dao.txt;

import agroludos.integration.dao.CertificatoSRCDAO;
import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.FileDAOFactory;

public class TxtDAOFactory extends FileDAOFactory{

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO();
	}

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

}