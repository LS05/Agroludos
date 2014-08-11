package agroludos.integration.dao.file.txt;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;

public class TxtDAOFactory extends FileFactory implements FileDAOFactory{

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO();
	}


}