package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.ApplicationController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.reqh.RequestContextFactory;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;

class FC implements FrontController{

	private ACFactory acFact;

	private RequestContextFactory reqFact;

	FC(RequestContextFactory reqFact, ACFactory acFact){
		this.reqFact = reqFact;
		this.acFact = acFact;
	}

	@Override
	public void eseguiRichiesta(AgroRequest request, AgroResponse response) {
		AgroRequestContext requestContext = this.reqFact.createRequestContext(request);
		ApplicationController ac = this.acFact.getAC();
		AgroResponseContext responseContext = ac.gestisciRichiesta(requestContext);
		responseContext.setResponse(response);
		ac.gestisciRisposta(requestContext, responseContext);
	}
}