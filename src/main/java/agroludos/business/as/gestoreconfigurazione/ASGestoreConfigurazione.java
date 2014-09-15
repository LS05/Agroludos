package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DBFactoryException;
import agroludos.exceptions.DatabaseException;
import agroludos.integration.dao.file.FConfigurazioneDAO;
import agroludos.integration.dao.file.FileDAOFactory;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;
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

		// TODO Aggiungere controlli sui dati dei parametri

		if(this.fileConf.creaConfigurazione(dbto)){
			this.sysConf.setTipoDB(dbto.getTipo());
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
		} catch (DBFactoryException e) {
			throw new DatabaseException(e.getMessage(), e.getCause());
		}

		return res;
	}
}