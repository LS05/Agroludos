package agroludos.presentation.controller;

import adisys.server.req.mapper.CommandMapper;
import agroludos.presentation.reqresh.AdiRequestContext;

abstract public class ApplicationController extends CommandMapper{
	
	ApplicationController(){
		super();
	}

	public Object gestisciRichiesta(AdiRequestContext request) {
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