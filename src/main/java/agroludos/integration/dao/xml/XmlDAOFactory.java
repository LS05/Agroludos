package agroludos.integration.dao.xml;

import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;

public class XmlDAOFactory extends FileDAOFactory {

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return new XmlConfigurazioneDAO();
	}

}
