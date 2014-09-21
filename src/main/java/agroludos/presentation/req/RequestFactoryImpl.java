package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class RequestFactoryImpl implements RequestFactory{
	
	private AgroSession session;
	
	public RequestFactoryImpl(AgroSession session) {
		this.session = session;
	}
	
	@Override
	public SimpleRequest createSimpleRequest(String commandName, String fromName){
		return new SimpleRequestImpl(commandName, fromName, this.session);
	}
	
	@Override
	public DataRequest createDataRequest(AgroludosTO data, String commandName, String fromName){
		return new FrameRequest(data, commandName, fromName, this.session);
	}
}
