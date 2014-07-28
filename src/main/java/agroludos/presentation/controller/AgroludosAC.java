package agroludos.presentation.controller;

import agroludos.req.mapper.CommandMapper;
import agroludos.presentation.reqresh.AgroRequestContext;

abstract public class AgroludosAC extends CommandMapper{
	
	AgroludosAC(){
		super();
	}

	public Object gestisciRichiesta(AgroRequestContext request) {
		//request.validate();
		//String command = request.getCommand();
		
		return execute(request);
		
		//Command command = commandMapper.getCommand(commandName);
		
		// Invoke Command
        //responseContext = command.execute(requestContext);
		 
		// Identify View Name
		//CommandMap  mapEntry = commandMapper.getCommandMap(commandName);
		//String viewName = mapEntry.getViewName();
		//responseContext.setViewName("");
	}
}