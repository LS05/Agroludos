package agroludos.presentation.reqh;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.req.SimpleRequest;

class RequestContextFactoryImpl implements RequestContextFactory {

	private DataRequestContextImpl dataRequest;

	private EmptyRequestContextImpl emptyRequest;

	public AgroRequestContext createRequestContext(AgroRequest request) {
		AgroRequestContext res = null;

		if(request instanceof DataRequest){
			this.dataRequest = new DataRequestContextImpl();
			this.dataRequest.initialize(request);
			res = dataRequest;
		}else if(request instanceof SimpleRequest){
			this.emptyRequest = new EmptyRequestContextImpl();
			this.emptyRequest.initialize(request);
			res = this.emptyRequest;
		}

		return res;
	}
}
