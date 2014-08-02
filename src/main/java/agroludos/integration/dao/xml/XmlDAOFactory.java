package agroludos.integration.dao.xml;

import agroludos.integration.dao.config.ConfigurazioneDAOFactory;
import agroludos.integration.dao.config.FConfigurazioneDAO;

public class XmlDAOFactory extends ConfigurazioneDAOFactory {

	@Override
	public FConfigurazioneDAO getConfigurazioneDAO() {
		return new XmlConfigurazioneDAO();
	}

}
