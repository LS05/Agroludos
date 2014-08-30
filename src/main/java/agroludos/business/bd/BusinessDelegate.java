package agroludos.business.bd;

import agroludos.exceptions.ApplicationException;
import agroludos.presentation.req.mapper.CommandMapperI;
import agroludos.to.AgroludosTO;

public class BusinessDelegate{
	
	private CommandMapperI cmdMap;
	
	public Object gestisciServizio(String servizio) throws ApplicationException{
		this.setClassMethod(servizio);
		this.cmdMap.setArgs(null);
		try {
			return this.cmdMap.execute();
		} catch (Throwable e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	public Object gestisciServizio(String servizio, AgroludosTO to) throws ApplicationException{
		this.setClassMethod(servizio);
		this.cmdMap.setArgs(to);
		try {
			return this.cmdMap.execute();
		} catch (Throwable e) {
			throw new ApplicationException(e.getMessage());
		}
	}
	
	private void setClassMethod(String servizio){
		this.cmdMap.setMethod(servizio);
		this.cmdMap.setObj(this);
	}
	
	public void setCmdMap(CommandMapperI cmdMap) {
		this.cmdMap = cmdMap;
	}
}
