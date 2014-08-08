package agroludos.business.bd;

import agroludos.req.mapper.CommandMapperI;
import agroludos.to.AgroludosTO;

public class BusinessDelegate{
	
	private CommandMapperI cmdMap;
	
	public Object gestisciServizio(String servizio) {
		this.setClassMethod(servizio);
		return this.cmdMap.execute();
	}
	
	public Object gestisciServizio(String servizio, AgroludosTO to) {
		this.setClassMethod(servizio);
		this.cmdMap.setArgs(to);
		return this.cmdMap.execute();
	}
	
	private void setClassMethod(String servizio){
		this.cmdMap.setMethod(servizio);
		this.cmdMap.setObj(this);
	}
	
	public void setCmdMap(CommandMapperI cmdMap) {
		this.cmdMap = cmdMap;
	}
}
