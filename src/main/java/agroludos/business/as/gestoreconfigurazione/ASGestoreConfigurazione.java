package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.db.DBConfigurazioneDAO;
import agroludos.integration.dao.db.DBDAOFactory;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;

class ASGestoreConfigurazione extends AgroludosAS implements LConfigurazione, SConfigurazione{

	private FileDAOFactory fileDaoFact;
	private FConfigurazioneDAO fileConf;

	private SystemConf sysConf;

	ASGestoreConfigurazione(SystemConf sysConf, FileFactory filefact){
		this.sysConf = sysConf;
		this.fileDaoFact = filefact.getDAOFactory(this.sysConf.getTipoConf());
		this.fileConf = this.fileDaoFact.getConfigurazioneDAO();
	}

	@Override
	public boolean inserisciConfigurazione(DatabaseTO dbto) throws DatabaseException {
		boolean res = false;
		DBDAOFactory dbDAO = null;
		DBConfigurazioneDAO dbConf = null; 

		// TODO Aggiungere controlli sui dati dei parametri

		if(this.fileConf.creaConfigurazione(dbto)){
			try {
				this.sysConf.setTipoDB(dbto.getTipo());
				dbDAO = this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
				dbConf = dbDAO.getConfigurazioneDAO();
				ConfigurazioneTO conf = this.toFact.createConfigurazioneTO(); 
				conf.setPathConf(this.fileConf.getConfPath());
				conf.setNomeDB(dbto.getNome());
				conf.setUserDB(dbto.getUsername());
				conf.setPwdDB(dbto.getPassword());
				conf.setPortaDB(dbto.getPorta());
				conf.setServerDB(dbto.getServer());
				conf.setTipoDB(dbto.getTipo());
				res = dbConf.addConfigurazioneDB(conf);
			} catch (DatabaseException e) {
				this.sysConf.setTipoDB("");
				throw e;
			}
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

	@Override
	public boolean testConnessioneDB() throws DatabaseException {
		boolean res = false;

		try {
			this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
			res = true;
		} catch (DatabaseException e) {
			throw e;
		}

		return res;
	}
}