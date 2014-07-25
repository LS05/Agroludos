package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;

class ASConfigurazione implements LConfigurazione {
	private DAOFactory daoFact;
	private ConfigurazioneDAO daoConf;
	
	ASConfigurazione(){
		this.daoFact = DAOFactory.getDAOFactory("");
		this.daoConf = this.daoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkConfigurazione() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getTipoDB() {
		// TODO Auto-generated method stub
		return null;
	}
}
