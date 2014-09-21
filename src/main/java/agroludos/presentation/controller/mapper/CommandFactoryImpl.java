package agroludos.presentation.controller.mapper;

import agroludos.exceptions.CommandFactoryException;
import agroludos.presentation.controller.mapper.xml.CommandParser;

class CommandFactoryImpl implements CommandFactory{
	private CommandParser cmdParser;
	private CommandImpl command;

	CommandFactoryImpl(CommandParser cmdParser){
		this.cmdParser = cmdParser;
	}

	@Override
	public Command getCommand(String commandName, String fromName) throws CommandFactoryException {
		String className = this.cmdParser.getClassName(commandName);
		String succView = this.cmdParser.getSuccView(commandName, fromName);
		String failView = this.cmdParser.getFailView(commandName, fromName);
		
		this.command = new CommandImpl();
		
		this.command.setClassName(className);
		this.command.setSuccView(succView);
		this.command.setFailView(failView);
		
		return this.command;
	}
}
