package agroludos.presentation.views;

import javafx.stage.Stage;
import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.system.ReqConf;
import agroludos.system.ErrorsConf;
import agroludos.to.AgroludosTO;

class Controller {	
	private static Navigator nav;
	private static FrontController frontController;
	private static RequestFactory reqFact;
	private static ResponseFactory respFact;
	private static ReqConf reqConf;
	private static ErrorsConf errConf;

	AgroResponse getRiposta(){
		return respFact.createResponse();
	}

	AgroRequest getRichiesta(String commandName, String viewName){
		String richiesta = reqConf.getRequest(commandName);
		return reqFact.createSimpleRequest(richiesta, viewName);	
	}

	AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		String richiesta = reqConf.getRequest(commandName);
		return reqFact.createDataRequest(param, richiesta, viewName);
	}

	void eseguiRichiesta(AgroRequest request, AgroResponse response) {
		frontController.eseguiRichiesta(request, response);
	}
	
	String getCommandName(String commandName){
		return reqConf.getRequest(commandName);
	}

	String getError(String errorName) {
		return errConf.getError(errorName);
	}

	void setVista(String viewName){
		nav.setVista(viewName);
	}

	void setVista(String viewName, AgroludosTO to){
		nav.setVista(viewName, to);
	}

	void closeVista(String viewName){
		nav.closeVista(viewName);
	}
	
	public Stage getStage(String viewName) {
		return nav.getStage(viewName);
	}

	public void setNav(Navigator navigator) {
		nav = navigator;
	}

	public void setFrontController(FrontController fc){
		frontController = fc;
	}

	public void setReqFact(RequestFactory rFact) {
		reqFact = rFact;
	}

	public void setRespFact(ResponseFactory respFactory) {
		respFact = respFactory;
	}

	public void setMainStage(Stage stage) {
		nav.setMainStage(stage);
	}

	public void setReqConf(ReqConf reqConfig) {
		reqConf = reqConfig;
	}

	public void setErrConf(ErrorsConf rulesConfig) {
		errConf = rulesConfig;
	}
}