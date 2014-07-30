package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.integration.dao.FileDAOFactory;
import agroludos.to.DatabaseTO;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{
	private FileDAOFactory fileDaoFact;
	private DAOFactory daoFact;

	private FConfigurazioneDAO daoConf;

	ASGestoreConfigurazione(){
		this.fileDaoFact = FileDAOFactory.getDAOFactory("xml");
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
