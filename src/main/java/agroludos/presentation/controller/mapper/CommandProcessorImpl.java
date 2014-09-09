package agroludos.presentation.controller.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.presentation.resph.AgroResponseContext;
import agroludos.presentation.resph.ResposeContextFactory;

class CommandProcessorImpl implements CommandProcessor{
	private CommandMap cmdMap;
	private ResposeContextFactory respFact;
	private AgroResponse response;

	CommandProcessorImpl(){
		ResponseFactory respFact = new ResponseFactory();
		this.response = respFact.createResponse();
	}

	@Override
	public AgroResponseContext invoke(Command command, AgroRequestContext request) {
		AgroResponseContext responseContext = null;
		try {
			this.cmdMap = new CommandMap(this.mainClass.getClass());
			Method m = this.cmdMap.getMethod(request.getCommandName());
			this.response.setResponse(m.invoke(this.mainClass, request));
			this.response.setViewPath(command.getSuccPath());
			this.response.setViewName(command.getViewName());
			responseContext = this.respFact.createResponseContext(this.response);
		} catch (IllegalAccessException| IllegalArgumentException
				| InvocationTargetException e) {
			this.response.setViewPath(command.getFailPath());
			responseContext = this.respFact.createResponseContext(this.response);
		}
		
		return responseContext;
	}

	public void setMainClass(MainCommandMap mainClass) {
		this.mainClass = mainClass;
	}

	public void setRespFact(ResposeContextFactory respFact) {
		this.respFact = respFact;
	}
}