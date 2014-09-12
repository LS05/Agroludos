package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class RequestFactoryImpl implements RequestFactory{
	
	@Override
	public DataRequest createDataRequest(AgroludosTO data, String commandName){
		return new FrameRequest(data, commandName);
	}
	
	@Override
	public SimpleRequest createSimpleRequest(String commandName){
		return new SimpleRequestImpl(commandName);
	}
}
