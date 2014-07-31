package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.integration.dao.DAOFactory;
import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.integration.dao.FileDAOFactory;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{
	private FileDAOFactory fileDaoFact;
	private FConfigurazioneDAO fileConf;
	

	ASGestoreConfigurazione(){
		this.fileDaoFact = FileDAOFactory.getDAOFactory("xml");
		this.fileConf = fileDaoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection() {
		return true;
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) {
		boolean res = false;
		DAOFactory daoFact = null;
		ConfigurazioneDAO dbConf = null; 
		
		// TODO Aggiungere controlli sui dati dei parametri
		
		if(this.fileConf.creaConfigurazione(dbto)){
			daoFact = DAOFactory.getDAOFactory(dbto.getTipo());
			dbConf = daoFact.getConfigurazioneDAO();
			ConfigurazioneTO conf = TOFactory.getConfigurazioneTO(); 
			conf.setPathConf(this.fileConf.getConfPath());
			conf.setNomeDB(dbto.getNome());
			conf.setUserDB(dbto.getUsername());
			conf.setPwdDB(dbto.getPassword());
			conf.setPortaDB(dbto.getPorta());
			conf.setServerDB(dbto.getServer());
			conf.setTipoDB(dbto.getTipo());
			dbConf.addConfigurazioneDB(conf);
		}
		
		return res;
	}

	@Override
	public boolean testConfigurazione() {
		return false;
	}

}
