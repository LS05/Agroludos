package agroludos.presentation.req;

import java.util.Map;

public class RequestFactory {
	
	private RequestFactory(){ }
	
	public DataRequest createDataRequest(Map<String, String> data, String commandName){
		return new FrameRequest(data, commandName);
	}
	
	public AgroRequest createRequest(String commandName){
		return new SimpleRequest(commandName);
	}
}
