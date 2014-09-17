package agroludos.presentation.controller;

import agroludos.exceptions.ApplicationException;
import agroludos.exceptions.CommandFactoryException;
import agroludos.exceptions.EnrichableException;
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
	
	@Override
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request) {
		AgroResponseContext response = null;
		Command command = null;
		String commandName = request.getCommandName();
		
		try {
			command = this.commandFactory.getCommand(commandName);
			response = this.commandProcessor.invoke(command, request);
		} catch (CommandFactoryException e) {
			
			// TODO Eccezione di programmazione
			// Il servizio richiesto (commandName) non è presente nel file CommandFactory.xml
			throw new EnrichableException("gestisciRichiesta", "E1", "Errore in ApplicationControllerImpl.gestisciRichiesta()", e);
		} catch (ApplicationException e) {
			
			// TODO Eccezione di programmazione
			// Sollevata solo in caso in cui ho una ServiceNotFoundException, IllegalAccess o IllegalArgument
			// quindi da qui il programma deve chiudersi; 
			e.printStackTrace();
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
		} catch(Exception e) {
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