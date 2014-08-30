package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.AgroludosAC;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.reqh.RequestContextFactory;

class FC implements FrontController{

	private ACFactory acFact;

	private RequestContextFactory reqFact;

	FC(RequestContextFactory reqFact, ACFactory acFact){
		this.reqFact = reqFact;
		this.acFact = acFact;
	}

	public Object eseguiRichiesta(AgroRequest request){	
		AgroRequestContext requestContext = this.reqFact.createRequestContext(request);
		AgroludosAC ac = this.acFact.getAC(requestContext);
		return ac.gestisciRichiesta(requestContext);
	}
}
