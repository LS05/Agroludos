package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.AgroludosAC;
import agroludos.presentation.controller.SpringACFactory;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.presentation.reqresh.RequestContextFactory;

class FC implements FrontController{
	
	private SpringACFactory acFact;
	
	private RequestContextFactory reqFact;
	
	FC(RequestContextFactory reqFact, SpringACFactory acFact){
		this.reqFact = reqFact;
		this.acFact = acFact;
	}

	public Object eseguiRichiesta(AgroRequest request){
		
		AgroRequestContext requestContext = this.reqFact.createRequestContext(request);
		
		this.acFact.setAgroRequestContext(requestContext);
		
		AgroludosAC ac = this.acFact.getAC();
	
		return ac.gestisciRichiesta(requestContext);
	}
}
