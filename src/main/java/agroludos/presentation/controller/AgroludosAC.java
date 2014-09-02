package agroludos.presentation.controller;

import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.controller.mapper.CommandFactory;
import agroludos.presentation.controller.mapper.CommandProcessor;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;

import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.Navigator;

class AgroludosAC implements ApplicationController{
	private CommandFactory commandFactory;
	private Command command;
	private CommandProcessor commandProcessor;
	private Navigator nav;

	@Override
	public AgroResponseContext gestisciRichiesta(AgroRequestContext request) {
		AgroResponseContext response = null;
		String commandName = request.getCommandName();
		this.command = this.commandFactory.getCommand(commandName);

		try {
			response = this.commandProcessor.invoke(this.command, request);
		} catch (Throwable e) {
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
			// Handle Exception
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