package agroludos.business.as;

import agroludos.integration.dao.db.DBFactory;
import agroludos.system.RulesErrorsConf;
import agroludos.system.SystemConf;
import agroludos.to.TOFactory;

/**
 * Classe di supporto per tutti gli Application Service di Agroludos
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosAS {
	protected SystemConf sysConf;
	protected RulesErrorsConf errConf;
	protected DBFactory dbFact;
	protected TOFactory toFact;
	
	/**
	 * @see SystemConf
	 * @param sysConf
	 */
	public void setSysConf(SystemConf sysConf) {
		this.sysConf = sysConf;
	}
	
	/**
	 * @see DBFactory
	 * @param dbFact
	 */
	public void setDbFact(DBFactory dbFact) {
		this.dbFact = dbFact;
	}
	
	/**
	 * @see TOFactory
	 * @param toFact
	 */
	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}

	/**
	 * @see RulesErrorsConf
	 * @param errConf
	 */
	public void setErrConf(RulesErrorsConf errConf) {
		this.errConf = errConf;
	}
	
}