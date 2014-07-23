package agroludos.integration.dao.txt;

import agroludos.integration.dao.FileDAO;
import agroludos.integration.dao.FileDAOFactory;

public class TxtDAOFactory extends FileDAOFactory{

	@Override
	public FileDAO getCertificatoSRCDAO() {
		return new TxtCertificatoSRCDAO();
	}

}