package agroludos.presentation.controller.mapper;

import agroludos.presentation.controller.mapper.xml.CommandParser;

class CommandFactoryImpl implements CommandFactory{
	private CommandParser cmdParser;
	private CommandImpl command;

	CommandFactoryImpl(CommandParser cmdParser){
		this.cmdParser = cmdParser;
	}

	@Override
	public Command getCommand(String commandName) {
		String className = this.cmdParser.getClassName(commandName);
		String failPath = cmdParser.getFailPath(commandName);
		String succPath = cmdParser.getSuccPath(commandName);
		String viewName = cmdParser.getSuccView(commandName);
		
		this.command = new CommandImpl();
		this.command.setFailPath(failPath);
		this.command.setSuccessPath(succPath);
		this.command.setClassName(className);
		this.command.setViewName(viewName);
		
		return this.command;
	}
}
