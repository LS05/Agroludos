package agroludos.business.as;

import agroludos.integration.dao.db.DBFactory;
import agroludos.system.RulesErrorsConf;
import agroludos.system.SystemConf;
import agroludos.to.TOFactory;

public abstract class AgroludosAS {
	protected SystemConf sysConf;
	protected RulesErrorsConf errConf;
	//TODO Da modificare in interfaccia
	protected DBFactory dbFact;
	protected TOFactory toFact;
	
	public void setSysConf(SystemConf sysConf) {
		this.sysConf = sysConf;
	}
	
	public void setDbFact(DBFactory dbFact) {
		this.dbFact = dbFact;
	}
	
	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}

	public void setErrConf(RulesErrorsConf errConf) {
		this.errConf = errConf;
	}
	
}