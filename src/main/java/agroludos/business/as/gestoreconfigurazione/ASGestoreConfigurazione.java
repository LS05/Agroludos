package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.config.ConfigurazioneDAODB;
import agroludos.integration.dao.config.ConfigurazioneDAOFactory;
import agroludos.integration.dao.config.FConfigurazioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.system.SystemConf;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{
	private ConfigurazioneDAOFactory fileDaoFact;
	private FConfigurazioneDAO fileConf;
	

	ASGestoreConfigurazione(){
		this.fileDaoFact = ConfigurazioneDAOFactory.getDAOFactory();
		this.fileConf = fileDaoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection() {
		return false;
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) {
		boolean res = false;
		DBDAOFactory daoFact = null;
		ConfigurazioneDAODB dbConf = null; 
		
		// TODO Aggiungere controlli sui dati dei parametri
		
		if(this.fileConf.creaConfigurazione(dbto)){
			SystemConf sysConf = SystemConf.getInstance();
			sysConf.setTipoDB(dbto.getTipo());
			daoFact = DBDAOFactory.getDAOFactory();
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
