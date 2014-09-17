package agroludos.presentation.req;

import agroludos.to.AgroludosTO;

class RequestFactoryImpl implements RequestFactory{
	
	private AgroSession session;
	
	public RequestFactoryImpl(AgroSession session) {
		this.session = session;
	}
	
	@Override
	public DataRequest createDataRequest(AgroludosTO data, String commandName){
		return new FrameRequest(data, commandName, this.session);
	}
	
	@Override
	public SimpleRequest createSimpleRequest(String commandName){
		return new SimpleRequestImpl(commandName, this.session);
	}
}
