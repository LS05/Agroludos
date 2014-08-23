package agroludos.business.as;

import agroludos.integration.dao.db.DBFactory;
import agroludos.system.SystemConf;
import agroludos.to.TOFactory;

public abstract class AgroludosAS {
	protected SystemConf sysConf;
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
	
}