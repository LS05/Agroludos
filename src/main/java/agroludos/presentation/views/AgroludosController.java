package agroludos.presentation.views;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.RequestFactory;

public class AgroludosController {
	protected static Navigator nav;
	protected static FrontController frontController;
	protected static RequestFactory reqFact;
	
	public void setNav(Navigator navigator) {
		nav = navigator;
	}

	public void setFrontController(FrontController fc){
		frontController = fc;
	}

	public void setReqFact(RequestFactory rFact) {
		reqFact = rFact;
	}
}