package agroludos.integration.dao.certificato;

import agroludos.integration.dao.file.CertificatoSRCDAO;

public abstract class CertificatoDAOFactory {
	private static CertificatoDAO certDAO = CertificatoDAO.getInstance();
	
	public static CertificatoDAOFactory getDAOFactory(){
		return certDAO.getDAOFactory();
	}
	
	public abstract CertificatoSRCDAO getCertificatoSRCDAO();
}
