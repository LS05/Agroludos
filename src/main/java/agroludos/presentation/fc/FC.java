package agroludos.presentation.fc;

import agroludos.presentation.controller.ACFactory;
import agroludos.presentation.controller.AgroludosAC;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.presentation.reqresh.RequestContextFactory;

public class FC {
	private static FC frontControllerInstance;

	private FC(){ }

	public static FC getInstance(){
		if(frontControllerInstance == null)
			frontControllerInstance = new FC();
		return frontControllerInstance;
	}

	public Object eseguiRichiesta(AgroRequest request){
		
		RequestContextFactory reqFact = RequestContextFactory.getInstance();
		
		ACFactory acFact = ACFactory.getInstance();
		
		AgroRequestContext requestContext = reqFact.createRequestContext(request);
		
		AgroludosAC ac = acFact.getAC(requestContext);
		
		return ac.gestisciRichiesta(requestContext);
	}
}
