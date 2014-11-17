package agroludos.presentation.views;

import javafx.stage.Stage;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.to.AgroludosTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

/**
 * Classe astratta che rappresenta un generico controller delle view. <br>
 * Sono implementati alcuni metodi che permettono di nascondere alcune dipendeze in tutti i controller delle view.
 * La classe inoltre fornisce a tutti i controller il {@link TOFactory} utile per ottenere le istanze dei 
 * Transfer Object.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosController {

	/**
	 * Riferimento alla classe controller che incapsula alcune componenti importanti per l'interazione
	 * con l'utente come: richieste, front controller.
	 */
	private static Controller controller;

	/**
	 * Rappresente l'utente attualmente loggato.
	 */
	private static UtenteTO utente;

	/**
	 * Riferimento ad una classe che implementa il facotry per i Transfer Object.
	 */
	protected static TOFactory toFact;

	/**
	 * @see agroludos.presentation.views.Controller#getRichiesta(String, String)
	 */
	protected AgroRequest getRichiesta(String commandName, String viewName){
		return controller.getRichiesta(commandName, viewName);	
	}

	/**
	 * @see agroludos.presentation.views.Controller#getRichiesta(AgroludosTO, String, String)
	 */
	protected AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		return controller.getRichiesta(param, commandName, viewName);
	}

	/**
	 * Il metodo restituisce un'istanza di una classe che implementa l'interfaccia {@link AgroResponse}
	 * e rappresenta la classe che conterrà il risultato dell'esecuzione della richiesta.
	 * @return
	 */
	protected AgroResponse getRisposta(){
		return controller.getRiposta();
	}

	/**
	 * @see agroludos.presentation.views.Controller#getCommandName(String)
	 * @param commandName
	 * @return Il nome del comando corrispondente alla chiave passata come parametro
	 */
	protected String getCommandName(String commandName){
		return controller.getCommandName(commandName);
	}

	/**
	 * @see agroludos.presentation.views.Controller#getError(String)
	 */
	protected String getError(String errorName){
		return controller.getError(errorName);
	}

	/**
	 * @see agroludos.presentation.views.Controller#eseguiRichiesta(AgroRequest, AgroResponse)
	 */
	protected void eseguiRichiesta(AgroRequest request, AgroResponse response){
		controller.eseguiRichiesta(request, response);
	}

	/**
	 * @see agroludos.presentation.views.Controller#setVista(String)
	 */
	protected void setVista(String viewName){
		controller.setVista(viewName);
	}

	/**
	 * @see agroludos.presentation.views.Controller#setVista(String, AgroludosTO)
	 */
	protected void setVista(String viewName, AgroludosTO to){
		controller.setVista(viewName, to);
	}

	/**
	 * @see agroludos.presentation.views.Controller#closeVista(String) 
	 */
	protected void closeVista(String viewName){
		controller.closeVista(viewName);
	}

	/**
	 * @see agroludos.presentation.views.Controller#getStage(String)
	 */
	protected Stage getStage(String viewName){
		return controller.getStage(viewName);
	}

	/**
	 * @see agroludos.presentation.views.Controller#setMainStage(Stage)
	 */
	protected void setMainStage(Stage stage){
		controller.setMainStage(stage);
	}

	/**
	 * @see agroludos.presentation.views.Controller#getString(String)
	 */
	protected String getString(String key){
		return controller.getString(key);
	}

	/**
	 * Ritorna il riferimento all'utente attualmente loggato
	 * 
	 * @return L'utente attualmente loggato
	 */
	protected UtenteTO getUtente(){
		return utente;
	}

	/**
	 * Setta l'utente loggato
	 * 
	 * @param userTO L'utente loggato
	 */
	protected void setUtente(UtenteTO userTO){
		utente = userTO;
	}

	/**
	 * @see agroludos.presentation.views.Controller#closeVista(String)
	 */
	protected void close(){
		controller.closeVista(getViewName());
	}

	/**
	 * @see agroludos.presentation.views.Controller#getStage(String)
	 */
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
	 * Il metodo inizializza una view che utilizzerà i dati presenti all'interno del Transfer Object
	 * specificato come parametro.
	 * 
	 * @param mainTO I dati da utilizzare nella view
	 */
	protected abstract void initializeView(AgroludosTO mainTO);

	/**
	 * Il metodo inizializza la view passando il nome da assegnargli.
	 * 
	 * @param viewName Nome della view da settare
	 */
	protected abstract void initializeView(String viewName);

	/**
	 * Ritorna il nome della view.
	 * 
	 * @return Nome della view
	 */
	protected abstract String getViewName();

	/**
	 * Per i dialog il forward è utile solo in caso di errore. Perchè il dato
	 * viene gestito sempre dalla mainView.
	 *  
	 * @param request
	 * @param response
	 */
	public abstract void forward(AgroRequest request, AgroResponse response);

}