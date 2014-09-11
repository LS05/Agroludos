package agroludos.business.bd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ServiceHandlerException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandMap;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

public class ServiceHandler {
	private CommandMap cmdMap;
	private AgroludosService service;
	private AgroResponseContext response;
	
	void setService(AgroludosService service){
		this.service = service;
	}

	AgroResponseContext handleService(Command command, AgroRequestContext request) throws ServiceHandlerException{

		if(this.service != null){
			Object res = null;
			this.cmdMap = new CommandMap(this.service.getClass());
			Method m = this.cmdMap.getMethod(request.getCommandName());
			m.setAccessible(true);
			try {
				if(request.isParam()){	
					res = m.invoke(this.service, request.getData());
				} else {
					res = m.invoke(this.service);
				}
				this.response.setLogicalViewName(command.getSuccView());
				this.response.setData(res);
			} catch (IllegalAccessException e) { 
				throw new ServiceHandlerException(e.getMessage());
			} catch (IllegalArgumentException e){
				throw new ServiceHandlerException(e.getMessage());
			} catch (InvocationTargetException e) { 
				throw new ServiceHandlerException(e.getMessage());
			}
		}

		return this.response;
	}

	public void setResponse(AgroResponseContext response) {
		this.response = response;
	} 
}