package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.req.SimpleRequest;

class RequestContextFactoryImpl{

	private static DataRequestContextImpl dataRequest = new DataRequestContextImpl(); ;
	
	private static EmptyRequestContextImpl emptyRequest = new EmptyRequestContextImpl();
	
	public AgroRequestContext createRequestContext(AgroRequest request) {
		AgroRequestContext res = null;
		
		if(request instanceof DataRequest){
			res = dataRequest;
		}else if(request instanceof SimpleRequest){
			res = emptyRequest;
		}
		
		return res;
	}
}
