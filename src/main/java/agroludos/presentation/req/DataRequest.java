package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

public abstract class DataRequest extends AgroRequest{
	
	DataRequest(String commandName, boolean isParam){
		super(commandName, isParam);
	}
	
	public abstract AgroludosTO getData();
	
}
