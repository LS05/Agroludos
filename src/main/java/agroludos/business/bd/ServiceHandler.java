package agroludos.business.bd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ServiceHandlerException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandMap;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;

public class ServiceHandler {
	private CommandMap cmdMap;
	private AgroludosService service;
	private AgroResponse response;

	void setService(AgroludosService service){
		this.service = service;
		this.response = null;
	}

	AgroResponse handleService(Command command, AgroRequestContext request) throws ServiceHandlerException{

		if(this.service != null){
			this.cmdMap = new CommandMap(this.service.getClass());
			Method m = this.cmdMap.getMethod(request.getCommandName());
			m.setAccessible(true);
			try {
				if(request.isParam())	
					this.response.setResponse(m.invoke(this.service, request));
				else{
					Object test = m.invoke(this.service);
					System.out.println(test);
//					this.response.setResponse();
				}

			} catch (IllegalAccessException e) { 
				throw new ServiceHandlerException(e.getMessage());
			} catch (IllegalArgumentException e){
				throw new ServiceHandlerException(e.getMessage());
			} catch (InvocationTargetException e) { 
				throw new ServiceHandlerException(e.getMessage());
			}
		}
		this.response.setViewPath(command.getSuccPath());
		this.response.setViewName(command.getViewName());
		return this.response;
	}
}