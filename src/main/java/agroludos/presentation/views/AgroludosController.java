package agroludos.presentation.views;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.to.TOFactory;

public abstract class AgroludosController {
	protected static Navigator nav;
	protected static FrontController frontController;
	protected static RequestFactory reqFact;
	protected static ResponseFactory respFact;
	protected static TOFactory toFact;
	
	public void setNav(Navigator navigator) {
		nav = navigator;
	}

	public void setFrontController(FrontController fc){
		frontController = fc;
	}

	public void setReqFact(RequestFactory rFact) {
		reqFact = rFact;
	}

	public void setToFact(TOFactory toFactory) {
		toFact = toFactory;
	}
	
	public void setRespFact(ResponseFactory respFact) {
		AgroludosController.respFact = respFact;
	}
	
	public abstract void forward(AgroRequest request, AgroResponse response);
}