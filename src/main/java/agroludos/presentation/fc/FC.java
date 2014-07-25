package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.ApplicationController;
import agroludos.presentation.req.AdiRequest;
import agroludos.presentation.reqresh.AdiRequestContext;
import agroludos.presentation.reqresh.RequestContextFactory;

public class FC {
	private static FC frontControllerInstance;

	private FC(){ }

	public static FC getInstance(){
		if(frontControllerInstance == null)
			frontControllerInstance = new FC();
		return frontControllerInstance;
	}

	public Object eseguiRichiesta(AdiRequest request){
		
		RequestContextFactory reqFact = RequestContextFactory.getInstance();
		
		ACFactory acFact = ACFactory.getInstance();
		
		AdiRequestContext requestContext = reqFact.createRequestContext(request);
		
		ApplicationController ac = acFact.getAC(requestContext);
		
		return ac.gestisciRichiesta(requestContext);
	}
}
