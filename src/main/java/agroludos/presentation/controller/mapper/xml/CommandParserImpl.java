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

	private Command getCommand(String commandName) throws CommandFactoryException{
		Command res = null;

		for(Command c : this.parsedCommands.getCommand()){
			if(c.getName().equals(commandName)){
				res = c;
			}
		}
		
		if(res == null){
			throw new CommandFactoryException();
		}

		return res;
	}

	private From getFrom(Command command, String fromName) throws CommandFactoryException{
		From res = null;
		
		for(From f : command.getFrom()){
			if(f.getName().equals(fromName))
				res = f;
		}

		if(res == null){
			throw new CommandFactoryException("Non è possibile determinare la provenienza della richiesta. Proprietà nome non impostata o assente per l'elemento \"from\" della richiesta al servizio: " + command.getName() +  ". Controllare il file CommandFactory.xml!");
		} 
		
		return res;
	}

	@Override
	public String getClassName(String commandName) throws CommandFactoryException{
		String res = "";

		Command c = this.getCommand(commandName);
		res = c.getClasse();

		return res;
	}

	@Override
	public String getFailView(String commandName, String fromName) throws CommandFactoryException {
		String res = "";

		Command command = this.getCommand(commandName);
		From f = this.getFrom(command, fromName);
		res = f.getForward().getFailure();

		return res;
	}

	@Override
	public String getSuccView(String commandName, String fromName) throws CommandFactoryException {
		String res = "";

		Command command = this.getCommand(commandName);
		From f = this.getFrom(command, fromName);
		res = f.getForward().getSuccess();

		return res;
	}
}