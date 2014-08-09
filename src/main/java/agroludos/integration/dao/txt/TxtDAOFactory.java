package agroludos.integration.dao.txt;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAO;

public class TxtDAOFactory implements FileDAO{

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO();
	}


}