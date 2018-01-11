package agroludos.business.as.gestoreconfigurazione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.system.DatabaseException;
import agroludos.integration.dao.file.FileFactory;
import agroludos.system.SystemConf;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Application Service</b> e rappresenta il componente:
 * <b>Gestore di Competizione.</b><br /> 
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ASGestoreConfigurazione extends AgroludosAS implements LConfigurazione{

	private SystemConf sysConf;

	ASGestoreConfigurazione(SystemConf sysConf, FileFactory filefact){
		this.sysConf = sysConf;
	}

	@Override
	public boolean checkConfigurazione() {
		boolean res = false;
		String tipoDB = this.sysConf.getTipoDB();

		if(tipoDB.length() != 0){
			res = true;
		}

		return res;
	}

	@Override
	public boolean testConnessioneDB() throws DatabaseException {
		boolean res = false;

		this.dbFact.getDAOFactory(this.sysConf.getTipoDB());
		res = true;

		return res;
	}
}