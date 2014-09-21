package agroludos.presentation.controller;

import java.io.IOException;

import agroludos.exceptions.ApplicationException;
import agroludos.exceptions.CommandFactoryException;
import agroludos.exceptions.EnrichableException;
import agroludos.exceptions.RequestInitializationException;
import agroludos.exceptions.ViewNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandFactory;
import agroludos.presentation.controller.mapper.CommandProcessor;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.Navigator;

class ApplicationControllerImpl implements ApplicationController{
	private CommandFactory commandFactory;
	private CommandProcessor commandProcessor;
	private Navigator nav;

	private String getCommandName(AgroRequestContext request) throws RequestInitializationException{
		String res = request.getCommandName();
		
		if(res == "" || res == null){
			throw new RequestInitializationException();
		}
		
		return res;
	}
	
	private String getviewName(AgroRequestContext request) throws RequestInitializationException{
		String res = request.getviewName();
		
		if(res == "" || res == null){
			throw new RequestInitializationException();
		}
		
		return res;
	}
	
	@Override
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request) {
		AgroResponseContext response = null;
		Command command = null;
		
		//TODO Controllare quando il commandName è null (non presente nel file delle proprietà dulle richieste)
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