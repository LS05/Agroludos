package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.req.mapper.CommandMapperI;

class ApplicationController implements AgroludosAC{
	
	private CommandMapperI cmdMap;
	
	ApplicationController(){
		System.out.println("ApplicationController()");
	}

	public Object gestisciRichiesta(AgroRequestContext request) {
		Object res = null;
		String command = request.getCommand();
		this.cmdMap.setObj(this);
		this.cmdMap.setMethod(command);
		this.cmdMap.setArgs(request);
		
		try {
			res = this.cmdMap.execute();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void setCmdMap(CommandMapperI cmdMap) {
		this.cmdMap = cmdMap;
	}
}