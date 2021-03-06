package agroludos.presentation.views;

import javafx.stage.Stage;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.to.AgroludosTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

/**
 * Classe astratta che rappresenta un generico controller in modo tale che tutti i controller possano
 * implementare i metodi di initialize, getViewName e Forward()
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosController {

	private static Controller controller;

	private static UtenteTO utente;

	protected static TOFactory toFact;

	protected AgroRequest getRichiesta(String commandName, String viewName){
		return controller.getRichiesta(commandName, viewName);	
	}

	protected AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		return controller.getRichiesta(param, commandName, viewName);
	}

	protected AgroResponse getRisposta(){
		return controller.getRiposta();
	}

	protected String getCommandName(String commandName){
		return controller.getCommandName(commandName);
	}

	protected String getError(String errorName){
		return controller.getError(errorName);
	}

	protected void eseguiRichiesta(AgroRequest request, AgroResponse response){
		controller.eseguiRichiesta(request, response);
	}

	protected void setVista(String viewName){
		controller.setVista(viewName);
	}

	protected void setVista(String viewName, AgroludosTO to){
		controller.setVista(viewName, to);
	}

	protected void closeVista(String viewName){
		controller.closeVista(viewName);
	}

	protected Stage getStage(String viewName){
		return controller.getStage(viewName);
	}

	protected void setMainStage(Stage stage){
		controller.setMainStage(stage);
	}

	protected String getString(String key){
		return controller.getString(key);
	}

	protected UtenteTO getUtente(){
		return utente;
	}

	protected void setUtente(UtenteTO userTO){
		utente = userTO;
	}

	/**
	 * Se è un dialog con un bottone tipo "Chiudi" o "Annulla"
	 * deve essere necessariamente chiamato questo metodo
	 * altrimenti non è necessario
	 */
	protected void close(){
		controller.closeVista(getViewName());
	}

	protected void show(){
		controller.getStage(getViewName()).show();
	}
	public void setToFact(TOFactory toFactory) {
		toFact = toFactory;
	}

	public void setController(Controller ctrl) {
		controller = ctrl;
	}

	/**
	 * Metodo che inizializza la view e imposta i campi utilizzando il TO in input
	 * @param mainTO
	 */
	protected abstract void initializeView(AgroludosTO mainTO);

	/**
	 * Metodo che inizializza la view
	 * @param viewName
	 */
	protected abstract void initializeView(String viewName);

	/**
	 * Restituisce il nome della view
	 * @return
	 */
	protected abstract String getViewName();

	/**
	 * Il forward gestisce tutte le risposte delle richieste effettuate
	 *  
	 * @param request
	 * @param response
	 */
	public abstract void forward(AgroRequest request, AgroResponse response);

}