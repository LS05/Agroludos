package agroludos.integration.dao;

import agroludos.integration.dao.txt.TxtDAOFactory;
import agroludos.integration.dao.xml.XmlDAOFactory;

abstract public class FileDAOFactory {

	public static FileDAOFactory getDAOFactory(String tipoFile)
			throws IllegalArgumentException {
		if (tipoFile.equals("txt"))
			return new TxtDAOFactory();
		else if (tipoFile.equals("xml"))
			return new XmlDAOFactory();
		else
			throw new IllegalArgumentException();
	}

	public abstract CertificatoSRCDAO getCertificatoSRCDAO();

	public abstract ConfigurazioneDAO getConfigurazioneDAO();
}
