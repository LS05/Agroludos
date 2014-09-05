package agroludos.business.bd;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.ApplicationException;
import agroludos.exceptions.BusinessDelegateException;
import agroludos.presentation.controller.mapper.CommandProcessor;
import agroludos.to.AgroludosTO;

public class BusinessDelegate{
	
	private CommandProcessor cmdMap;
	
	private AgroludosAS service;
	
	BusinessDelegate(String serviceName) throws BusinessDelegateException{
		
	}
	
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
	
	public void setCmdMap(CommandProcessor cmdMap) {
		this.cmdMap = cmdMap;
	}
}
