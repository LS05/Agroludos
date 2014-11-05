package agroludos.integration.dao.file.txt;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.system.Conf;
import agroludos.to.TOFactory;

public class TxtDAOFactory implements FileDAOFactory{
	
	private TOFactory toFact;
	private Conf sysConf;

	public TxtDAOFactory(TOFactory toFact, Conf sysConf){
		this.toFact = toFact;
		this.sysConf = sysConf;
	}
	
	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return null;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO(this.toFact, this.sysConf);
	}

}