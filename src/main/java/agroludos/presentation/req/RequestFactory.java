package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

public class RequestFactory {
	
	private RequestFactory(){ }
	
	public DataRequest createDataRequest(AgroludosTO data, String commandName){
		return new FrameRequest(data, commandName);
	}
	
	public AgroRequest createSimpleRequest(String commandName){
		return new SimpleRequest(commandName);
	}
}
