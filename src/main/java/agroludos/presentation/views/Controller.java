package agroludos.presentation.views;

import javafx.stage.Stage;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.system.Conf;
import agroludos.system.ReqConf;
import agroludos.system.RulesErrorsConf;
import agroludos.to.AgroludosTO;

/**
 * Classe di supporto per tutti i controller delle view. Contiene tutte le dipendenze che sono nascoste ai controller,
 * ma che questi utilizzeranno indirettamente per portare a termine compiti quali: l'esecuzione di una richiesta (servizio)
 * , l'utilizzo dei dati che rappresentano il risultato dell'esecuzione dei servizi e l'accesso a proprietà di sistema.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Controller {

	/**
	 * @see {@link Navigator}
	 */
	private static Navigator nav;

	/**
	 * @see {@link FrontController}
	 */
	private static FrontController frontController;

	/**
	 * @see {@link RequestFactory}
	 */
	private static RequestFactory reqFact;

	/**
	 * @see {@link ResponseFactory}
	 */
	private static ResponseFactory respFact;

	/**
	 * @see {@link ReqConf}
	 */
	private static ReqConf reqConf;

	/**
	 * @see {@link RulesErrorsConf}
	 */
	private static RulesErrorsConf errConf;

	/**
	 * @see {@link Conf}
	 */
	private static Conf conf;

	/**
	 * Restituisce un riferimento ad una classe che implementa l'interfaccia {@link AgroResponse} tramite il 
	 * {@link ResponseFactory}
	 * 
	 * @return Riferimento ad una classe che implementa l'interfaccia AgroResponse
	 */
	AgroResponse getRiposta(){
		return respFact.createResponse();
	}

	/**
	 * Il metodo permette di restituire un'istanza di una classe che implementa l'interfaccia {@link AgroRequest}
	 * e rappresenta la richiesta da eseguire. In questo caso la richiesta non è parametrica.
	 * 
	 * @param commandName Rappresenta il nome della richiesta da eseguire
	 * @param viewName Rappresenta il nome della view che esegue la richiesta
	 * @return Richiesta da eseguire
	 */
	AgroRequest getRichiesta(String commandName, String viewName){
		String richiesta = reqConf.getRequest(commandName);
		return reqFact.createSimpleRequest(richiesta, viewName);	
	}

	/**
	 * Il metodo restituisce un'istanza di una classe che implementa l'interfaccia {@link AgroRequest}
	 * e rappresenta la richiesta da eseguire. In questo caso la richiesta è parametrica.
	 * 
	 * @param param Istanza di una classe che rappresenta un Transfer Object e in particolare rappresenta i
	 * dati da utilizzare nella richiesta
	 * @param commandName Rappresenta il nome della richiesta da eseguire
	 * @param viewName Rappresenta il nome della view che esegue la richiesta
	 * @return Richiesta da eseguire
	 */
	AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		String richiesta = reqConf.getRequest(commandName);
		return reqFact.createDataRequest(param, richiesta, viewName);
	}

	/**
	 * Esegue la richiesta specificata, memorizzando il risultato della sua esecuzione nella risposta specificata
	 * utilzzando il {@link FrontController}
	 * 
	 * @param request Richiesta ad un servizio da eseguire
	 * @param response Risposta che rappresenta il risultato della sua esecuzione
	 */
	void eseguiRichiesta(AgroRequest request, AgroResponse response) {
		frontController.eseguiRichiesta(request, response);
	}

	/**
	 * @see agroludos.system.ReqConf#getRequest(String) 
	 */
	String getCommandName(String commandName){
		return reqConf.getRequest(commandName);
	}

	/**
	 * @see agroludos.system.RulesErrorsConf#getErrorMessage(String)
	 */
	String getError(String errorName) {
		return errConf.getErrorMessage(errorName);
	}

	/**
	 * @see agroludos.presentation.views.Navigator#setVista(String)
	 */
	void setVista(String viewName){
		nav.setVista(viewName);
	}

	/**
	 * @see agroludos.presentation.views.Navigator#setVista(String, AgroludosTO)
	 */
	void setVista(String viewName, AgroludosTO to){
		nav.setVista(viewName, to);
	}

	/**
	 * @see agroludos.presentation.views.Navigator#closeVista(String)
	 */
	void closeVista(String viewName){
		nav.closeVista(viewName);
	}

	/**
	 * @see agroludos.presentation.views.Navigator#getStage(String)
	 */
	Stage getStage(String viewName) {
		return nav.getStage(viewName);
	}

	/**
	 * @see agroludos.system.Conf#getString(String)
	 */
	String getString(String key){
		return conf.getString(key);
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

	public void setErrConf(RulesErrorsConf rulesConfig) {
		errConf = rulesConfig;
	}

	public static void setConf(Conf agConf) {
		conf = agConf;
	}
}