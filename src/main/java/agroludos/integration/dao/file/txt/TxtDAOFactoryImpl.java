package agroludos.integration.dao.file.txt;

import agroludos.integration.dao.file.CertificatoSRCDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.system.Conf;
import agroludos.to.TOFactory;

class TxtDAOFactoryImpl implements TxtDAOFactory, FileDAOFactory{
	
	private TOFactory toFact;
	private Conf sysConf;

	TxtDAOFactoryImpl(TOFactory toFact, Conf sysConf){
		this.toFact = toFact;
		this.sysConf = sysConf;
	}

	@Override
	public CertificatoSRCDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO(this.toFact, this.sysConf);
	}

}