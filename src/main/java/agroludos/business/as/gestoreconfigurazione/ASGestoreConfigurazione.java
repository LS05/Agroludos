package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.to.DatabaseTO;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{
	private DAOFactory daoFact;
	private ConfigurazioneDAO daoConf;
	
	ASGestoreConfigurazione(){
		this.daoFact = DAOFactory.getDAOFactory("");
		this.daoConf = this.daoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection(DatabaseTO dbto) {
		return false;
	}

}
