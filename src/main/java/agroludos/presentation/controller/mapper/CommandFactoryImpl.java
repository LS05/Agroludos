package agroludos.presentation.controller.mapper;

import agroludos.exceptions.system.CommandFactoryException;
import agroludos.presentation.controller.mapper.xml.CommandParser;

class CommandFactoryImpl implements CommandFactory{
	private CommandParser cmdParser;

	CommandFactoryImpl(CommandParser cmdParser){
		this.cmdParser = cmdParser;
	}

	@Override
	public Command getCommand(String commandName, String viewName) throws CommandFactoryException {
		CommandImpl command = new CommandImpl();
		String className = this.cmdParser.getClassName(commandName);
		String succView = this.cmdParser.getSuccView(commandName, viewName);
		String failView = this.cmdParser.getFailView(commandName, viewName);

		command.setClassName(className);
		command.setSuccView(succView);
		command.setFailView(failView);

		return command;
	}
}