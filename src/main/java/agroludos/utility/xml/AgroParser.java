package agroludos.utility.xml;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * La classe utilizza la libreria JAXB per effettuare un mapping tra l'xml specificato
 * e le classi generate attraverso il file binding.xml prensente nella cartella resources
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 * @see <a href="https://docs.oracle.com/javase/tutorial/jaxb/intro/arch.html">https://docs.oracle.com/javase/tutorial/jaxb/intro/arch.html</a>
 */
public abstract class AgroParser {
	/**
	 * File xml da caricare
	 */
	protected File xmlFile;
	
	/**
	 * @see {@link javax.xml.bind.Unmarshaller}
	 */
	protected Unmarshaller jaxbUnmarshaller;
	
	/**
	 * Classe radice del file XML
	 */
	protected Object parseRes;

	/**
	 * Il costruttore si occupa di effettuare il marshalling e quindi mappare la
	 * struttura del file xml che verrà caricato in base al percorso impostato come
	 * parametro.
	 * 
	 * @param parseClass Classe che rappresenta la radice del file xml
	 * @param path Percorso del file xml
	 * @throws JAXBException
	 * @see <a href="https://docs.oracle.com/javase/7/docs/api/javax/xml/bind/JAXBException.html">
	 * https://docs.oracle.com/javase/7/docs/api/javax/xml/bind/JAXBException.html</a>
	 */
	protected AgroParser(Class<?> parseClass, String path) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(parseClass);
		this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Path xmlPath = Paths.get(path);
		this.xmlFile = new File(xmlPath.toString());
		this.parseRes = this.jaxbUnmarshaller.unmarshal(this.xmlFile);
	}
}