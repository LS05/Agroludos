package agroludos.presentation.controller.mapper.xml;

import javax.xml.bind.JAXBException;

import agroludos.exceptions.CommandFactoryException;
import agroludos.utility.xml.AgroParser;

class CommandParserImpl extends AgroParser implements CommandParser {

	private Commands parsedCommands;

	CommandParserImpl() throws JAXBException {
		super(Commands.class, "src/main/java/agroludos/presentation/controller/mapper/xml/CommandFactory.xml");
		this.parsedCommands = (Commands) this.parseRes;
	}

	private Command getCommand(String commandName){
		Command res = null;

		for(Command c : this.parsedCommands.getCommand()){
			if(c.getName().equals(commandName)){
				res = c;
			}
		}

		return res;
	}

	private boolean hasCommand(String commandName){
		boolean res = false;

		Command c = this.getCommand(commandName);
		
		if(c != null) {
			res = true;
		} else {
			res = false;
		}
		
		return res;
	}

	@Override
	public String getClassName(String commandName) throws CommandFactoryException{
		String res = "";

		if(this.hasCommand(commandName)){
			Command c = this.getCommand(commandName);
			res = c.getClasse();
		} else {
			throw new CommandFactoryException();
		}

		return res;
	}

	@Override
	public String getFailView(String commandName) throws CommandFactoryException {
		String res = "";
		if(this.hasCommand(commandName)){
			Command command = this.getCommand(commandName);
			res = command.getForward().getFailure();
		} else {
			throw new CommandFactoryException();
		}
		return res;
	}

	@Override
	public String getSuccView(String commandName) throws CommandFactoryException {
		String res = "";
		if(this.hasCommand(commandName)){
			Command command = this.getCommand(commandName);
			res = command.getForward().getSuccess();
		} else {
			throw new CommandFactoryException();
		}
		return res;
	}
}