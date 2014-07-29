package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.FileDAOFactory;
import agroludos.to.DatabaseTO;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{
	private FileDAOFactory daoFact;
	private ConfigurazioneDAO daoConf;
	
	ASGestoreConfigurazione(){
		this.daoFact = FileDAOFactory.getDAOFactory("xml");
		this.daoConf = this.daoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection(DatabaseTO dbto) {
		return false;
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) {		
		//controlli
		this.daoConf.creaConfigurazione(dbto);
		return false;
	}

}
