package agroludos.business.bd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.MethodNotFoundException;
import agroludos.exceptions.ServiceHandlerException;
import agroludos.exceptions.ServiceNotFoundException;
import agroludos.exceptions.ValidationException;
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

	AgroResponseContext handleService(Command command, AgroRequestContext request) throws ServiceHandlerException, ServiceNotFoundException{

		if(this.service != null){
			Object res = null;
			this.cmdMap = new CommandMap(this.service.getClass());
			try {
				Method service = this.cmdMap.getMethod(request.getCommandName());
				service.setAccessible(true);
				if(request.isParam()){	
					res = service.invoke(this.service, request.getData());
				} else {
					res = service.invoke(this.service);
				}
				this.response.setLogicalViewName(command.getSuccView());
				this.response.setData(res);
			} catch (IllegalAccessException e) { 
				throw new ServiceHandlerException(e.getMessage(), e.getCause());
			} catch (IllegalArgumentException e){
				throw new ServiceHandlerException(e.getMessage(), e.getCause());
			}  catch (MethodNotFoundException e) {
				throw new ServiceNotFoundException("Servizio " + request.getCommandName() + " non presente!", e);
			} catch (InvocationTargetException e) {
				//TODO Aggiungere "Gestore" per le eccezioni?
				//TODO Controllare se l'eccezione è di sistema o
				//di applicazione
				//Controllare se è un'eccezione di controllo-dati 
				//(o di un altro tipo particolare) 
				//allora passo il TO, altrimenti il messaggio.
				//Passare il messaggio o l'eccezione?
				this.response.setLogicalViewName(command.getFailView());

				Throwable cause = e.getCause();
				if(cause == null) {
					throw new IllegalStateException( 
							"Got InvocationTargetException, but the cause is null.", e);
				} else if(cause instanceof ValidationException) {
					ValidationException vEx = (ValidationException) cause;
					this.response.setData(vEx.getErrors());
				} else{
					this.response.setData(e.getTargetException().getMessage());
				}

			}
		}

		return this.response;
	}

	public void setResponse(AgroResponseContext response) {
		this.response = response;
	} 
}