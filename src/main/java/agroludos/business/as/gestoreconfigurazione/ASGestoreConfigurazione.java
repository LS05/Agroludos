package agroludos.business.as.gestoreconfigurazione;

import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

class ASGestoreConfigurazione implements LConfigurazione, SConfigurazione{

	private SystemConf sysConf;
	private FileDAOFactory fileDaoFact;
	private FConfigurazioneDAO fileConf;
	private TOFactory toFact;
	
	ASGestoreConfigurazione(SystemConf sysConf, FileFactory filefact){
		this.sysConf = sysConf;
		this.fileDaoFact = filefact.getDAOFactory(this.sysConf.getTipoConf());
		this.fileConf = this.fileDaoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean testDBConnection() {
		return false;
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) {
		boolean res = false;
		DBDAOFactory daoFact = null;
		DBConfigurazioneDAO dbConf = null; 
		
		// TODO Aggiungere controlli sui dati dei parametri
		
		if(this.fileConf.creaConfigurazione(dbto)){
			sysConf.setTipoDB(dbto.getTipo());
			//Sono arrivato a questo punto.
			daoFact = DBDAOFactory.getDAOFactory();
			dbConf = daoFact.getConfigurazioneDAO();
			ConfigurazioneTO conf = this.toFact.createConfigurazioneTO(); 
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
	public boolean checkConfigurazione() {
		boolean res = false;
		String tipoDB = this.sysConf.getTipoDB();
		
		if(!tipoDB.equals(""))
			res = true;
		
		return res;
	}
	
	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}
}