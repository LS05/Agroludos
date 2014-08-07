package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.req.mapper.CommandMapperI;

class ApplicationController implements AgroludosAC{
	
	private CommandMapperI cmdMap;
	
	ApplicationController(){
		System.out.println("ApplicationController()");
	}

	public Object gestisciRichiesta(AgroRequestContext request) {
		String command = request.getCommand();
		this.cmdMap.setObj(this);
		this.cmdMap.setMethod(command);
		if(request.isParam())
			this.cmdMap.setArgs(request);
		return this.cmdMap.execute();
	}
	
	public void setCmdMap(CommandMapperI cmdMap) {
		this.cmdMap = cmdMap;
	}
}