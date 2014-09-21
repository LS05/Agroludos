package agroludos.presentation.views;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.to.TOFactory;

public abstract class Controller {

	//TODO Sono da nascondere ai controller.
	//I controller devono accedere con this. e non con static

	//TODO Eliminare i metodi set visibili alle sottoclassi
	
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
	
	public void setRespFact(ResponseFactory respFactory) {
		respFact = respFactory;
	}
}