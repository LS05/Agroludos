package agroludos.presentation.req;

import java.util.Map;

public class RequestFactory {
	
	private RequestFactory(){ }
	
	public AgroRequest createDFrameRequest(Map<String, String> data, String commandName){
		return new FrameRequest(data, commandName);
	}
	
	public AgroRequest createEFrameRequest(String commandName){
		return new FrameRequest(commandName);
	}
	
	public AgroRequest createSimpleRequest(String commandName){
		return new SimpleRequest(commandName);
	}
}
