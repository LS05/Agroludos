package agroludos.utility.xml;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import agroludos.presentation.controller.mapper.xml.Commands;

public abstract class AgroParser {
	protected File xmlFile;
	protected Unmarshaller jaxbUnmarshaller;
	protected Object parseRes;

	protected AgroParser() throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(Commands.class);
		this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Path xmlPath = Paths.get("src/main/java/agroludos/presentation/controller/mapper/xml/CommandFactory.xml");
		this.xmlFile = new File(xmlPath.toString());
		this.parseRes = this.jaxbUnmarshaller.unmarshal(this.xmlFile);
	}
}