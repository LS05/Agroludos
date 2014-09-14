package agroludos.presentation.controller.mapper.xml;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import agroludos.exceptions.BusinessComponentNotFoundException;

class CommandParserImpl implements CommandParser {

	private Commands commands;

	public CommandParserImpl() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Commands.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Path xmlPath = Paths.get("src/main/java/agroludos/presentation/controller/mapper/xml/CommandFactory.xml");
			// specify the location and name of xml file to be read
			File XMLfile = new File(xmlPath.toString());

			// this will create Java object - country from the XML file
			this.commands = (Commands) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Command getCommand(String commandName){
		Command res = null;

		for(Command c : commands.getCommand()){
			if(c.getName().equals(commandName)){
				res = c;
			}
		}

		return res;
	}

	private boolean hasCommand(String commandName){
		boolean res;

		Command c = this.getCommand(commandName);
		if(c != null)
			res = true;
		else
			res = false;

		return res;
	}

	@Override
	public String getClassName(String commandName) throws BusinessComponentNotFoundException{
		String res = "";

		if(this.hasCommand(commandName)){
			Command c = this.getCommand(commandName);
			res = c.getClasse();
		} else {
			throw new BusinessComponentNotFoundException("Servizio richiesto non presente!");
		}

		return res;
	}

	@Override
	public String getFailView(String commandName) throws BusinessComponentNotFoundException {
		String res = "";
		if(this.hasCommand(commandName)){
			Command command = this.getCommand(commandName);
			res = command.getForward().getFailure();
		} else {
			throw new BusinessComponentNotFoundException("Servizio richiesto non presente!");
		}
		return res;
	}

	@Override
	public String getSuccView(String commandName) throws BusinessComponentNotFoundException {
		String res = "";
		if(this.hasCommand(commandName)){
			Command command = this.getCommand(commandName);
			res = command.getForward().getSuccess();
		} else {
			throw new BusinessComponentNotFoundException("Servizio richiesto non presente!");
		}
		return res;
	}
}