package agroludos.presentation.controller;

import java.io.IOException;

import agroludos.exceptions.system.ApplicationException;
import agroludos.exceptions.system.CommandFactoryException;
import agroludos.exceptions.system.EnrichableException;
import agroludos.exceptions.system.RequestInitializationException;
import agroludos.exceptions.system.ViewNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandFactory;
import agroludos.presentation.controller.mapper.CommandProcessor;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.Navigator;

/**
 * <strong>Presentation Tier</strong><br />
 * La classe rappresenta un'implementazione dell'Application Controller (basandosi 
 * su quanto specificato nell'interfaccia {@link agroludos.presentation.controller.ApplicationController}).<br /><br />
 * Nel livello di presentazione di solito, sono importanti due decisioni:
 * <ul>
 * <li>Risolvere ed Eseguire la richiesta in arrivo con un'azione che la porti a temrmine.
 * E questa decisione è chiamata: <b>Action Management.</b></li>
 * <li>Scegliere la view appropriata su cui effettuare il dispatching. 
 * E questa decisione è chiamata: <b>View Management</b></li>
 * </ul>
 * Grazie all'Application Controller (AC) è possibile centralizzare il ritrovamento
 * e l'invocazione dei componenti per l'elaborazione delle richieste come i comandi e le view.
 * Tale strategia, posta all'interno dell'AC, migliora la modularità, la riusabilità e l'estendibilità del codice.
 * Per gestire le richieste è stato utilizzato il Context Object che facilita la comunicazione tra l'AC e il Front
 * Controller, nascondendo la tipologia di protocollo di comunicazione utilizzata nell'applicazione. 
 *
 * @see agroludos.presentation.reqh.AgroRequestContext
 * @see agroludos.presentation.resph.AgroResponseContext
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ApplicationControllerImpl implements ApplicationController{

	/**
	 * Factory per istanziare una classe Command
	 * 
	 * @see agroludos.presentation.controller.mapper.Command
	 */
	private CommandFactory commandFactory;

	/**
	 * Il CommandProcessor è utilizzato per eseguire
	 * tramite il metodo 
	 * {@link agroludos.presentation.controller.mapper.CommandProcessor#invoke(Command, AgroRequestContext)}
	 * un Command ottenuto con il CommandFactory.
	 * 
	 * @see agroludos.presentation.controller.mapper.Command
	 */
	private CommandProcessor commandProcessor;

	/**
	 * Il Navigator si occupa di effettuare il dispatching del risultato dell'esecuzione del servizio richiesto.
	 * Il dispatching avviene tramite il metodo 
	 * {@link agroludos.presentation.controller.ApplicationControllerImpl#dispatch(AgroRequest, AgroResponse, String)} 
	 */
	private Navigator nav;

	/**
	 * Il metodo si occupa di ritornare il nome del servizio richiesto. Il nome è ottenuto tramite il metodo
	 * {@link agroludos.presentation.reqh.AgroRequestContext#getCommandName()}. Se il nome non è impostato
	 * in fase di inizializzazione della richiesta viene sollevata una RequestInitializationException.
	 * 
	 * @param request Richiesta del servizio
	 * @return Nome del servizio da eseguire
	 * @throws RequestInitializationException Se il nome del comando non è presente
	 */
	private String getCommandName(AgroRequestContext request) throws RequestInitializationException{
		String res = request.getCommandName();

		if(res.isEmpty() || res == null){
			throw new RequestInitializationException();
		}

		return res;
	}

	/**
	 * Il metodo ritorna il nome della view richiesta. Il nome è ottenuto tramite il metodo 
	 * {@link agroludos.presentation.reqh.AgroRequestContext#getviewName()}. Se il nome non è impostato
	 * in fase di inizializzazione della richiesta viene sollevata una RequestInitializationException.
	 * 
	 * @param request Richiesta del servizio
	 * @return Nome della view
	 * @throws RequestInitializationException Nome della view non presente
	 */
	private String getviewName(AgroRequestContext request) throws RequestInitializationException{
		String res = request.getviewName();

		if(res.isEmpty() || res == null){
			throw new RequestInitializationException();
		}

		return res;
	}

	/**
	 * Il metodo si occupa di gestire una richiesta proveniente dal Front Controller.
	 * La gestione prevede l'utilizzo di un'istanza di una classe che implementa l'interfaccia Command 
	 * ottenuta utilizzando il {@link CommandFactory}.<br>
	 * In particolare il {@link CommandFactory} ottiene l'istanza di Command utilizzando i metodi {@link getViewName} 
	 * e {@getCommandName}. Ottenuta l'istanza viene eseguito il servizio richiesto con gli eventuali
	 * dati presenti all'interno dell'AgroRequestContext.
	 * Il risultato dell'esecuzione del servizio è garantita dal {@link CommandProcessor}.
	 * Infatti viene utilizzato il metodo 
	 * {@link agroludos.presentation.controller.mapper.CommandProcessor#invoke(Command, AgroRequestContext)}
	 * che ottiene la risposta che verrà restituita.
	 * In particolare la risposta è un'istanza di AgroResponseContext che verrà poi utilizzata per effettuare il 
	 * dispatching.
	 * 
	 * @see {@link agroludos.presentation.fc.FrontController}
	 * @see {@link agroludos.presentation.controller.mapper.Command}
	 * @see {@link agroludos.presentation.reqh.AgroRequestContext}
	 * @see {@link agroludos.presentation.resph.AgroResponseContext}
	 */
	@Override
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request) {
		AgroResponseContext response = null;
		Command command = null;
		String commandName = null;
		String viewName = null; 

		try{
			commandName = this.getCommandName(request);
			viewName = this.getviewName(request);
		} catch(RequestInitializationException e){
			throw new EnrichableException("gestisciRichiesta", "E3", "Errore in ApplicationControllerImpl.gestisciRichiesta()", e);
		}

		try {
			command = this.commandFactory.getCommand(commandName, viewName);
			response = this.commandProcessor.invoke(command, request);
		} catch (CommandFactoryException e) {

			// TODO Eccezione di programmazione
			// Il servizio richiesto (commandName) non è presente nel file CommandFactory.xml
			throw new EnrichableException("gestisciRichiesta", "E1", "Errore in ApplicationControllerImpl.gestisciRichiesta()", e);
		} catch (ApplicationException e) {

			// TODO Da controllare se è un'eccezione di programmazione
			// In caso in cui ho una BusinessComponentNotFoundException, ServiceNotFoundException, IllegalAccess o IllegalArgument
			// quindi da qui il programma deve chiudersi; 
			throw new EnrichableException("gestisciRichiesta", "E2", "Errore in ApplicationControllerImpl.gestisciRichiesta()", e);
		}

		return response;
	}

	@Override
	public void gestisciRisposta(AgroRequestContext requestContext,
			AgroResponseContext responseContext) {
		dispatch(requestContext.getRequest(), responseContext.getResponse(), responseContext.getLogicalViewName());
	}

	private void dispatch(AgroRequest request, AgroResponse response, String page) {
		AgroludosController dispatcher = null;

		try {
			dispatcher = this.nav.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		} catch (ViewNotFoundException e){

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setCommandProcessor(CommandProcessor commandProcessor) {
		this.commandProcessor = commandProcessor;
	}

	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

	public void setNav(Navigator nav) {
		this.nav = nav;
	}
}