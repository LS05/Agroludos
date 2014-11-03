package agroludos.integration.dao.file.txt;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.to.TOFactory;

public class TxtDAOFactory implements FileDAOFactory{
	
	private TOFactory toFact;

	public TxtDAOFactory(TOFactory toFact){
		this.toFact = toFact;
	}
	
	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO(this.toFact);
	}

}