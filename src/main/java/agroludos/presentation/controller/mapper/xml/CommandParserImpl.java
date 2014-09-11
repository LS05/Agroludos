package agroludos.presentation.controller.mapper.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class CommandParserImpl implements CommandParser {

	private Commands commands;

	public CommandParserImpl() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Commands.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			// specify the location and name of xml file to be read
			File XMLfile = new File("src/main/java/agroludos/presentation/controller/mapper/xml/CommandFactory.xml");

			// this will create Java object - country from the XML file
			this.commands = (Commands) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getClassName(String commandName){
		String res = "";

		for(Command c : commands.getCommand()){
			if(c.getName().equals(commandName))
				res = c.getClasse();
		}

		return res;
	}
	
	private Forward getRes(String commandName, String type){
		Forward res = null;

		for(Command c : commands.getCommand()){
			if(c.getName().equals(commandName)){
				for(Forward f : c.getForward()){
					if(f.getType().equals(type)){
						res = f;
						break;
					}
				}
			}		
		}
		
		return res;
	}
	
	@Override
	public String getFailView(String commandName) {
		Forward res = getRes(commandName, "failure");
		return res.getName();
	}
	
	@Override
	public String getSuccView(String commandName) {
		Forward res = getRes(commandName, "success");
		return res.getName();
	}
	
	
	
}