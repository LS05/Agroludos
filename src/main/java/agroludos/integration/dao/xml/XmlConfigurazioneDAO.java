package agroludos.integration.dao.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.to.DatabaseTO;

public class XmlConfigurazioneDAO implements ConfigurazioneDAO{

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {
		
		String filepath = "src/main/resources/xml/configurazione.xml";
		try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(filepath);
 
		// Get the root element
		Node company = doc.getFirstChild();
 
		// Get the staff element , it may not working if tag has spaces, or
		// whatever weird characters in front...it's better to use
		// getElementsByTagName() to get it directly.
		// Node staff = company.getFirstChild();
 
		// Get the staff element by tag name directly
		Node database = doc.getElementsByTagName("database").item(0);
 
		// loop the staff child node
		NodeList list = database.getChildNodes();
 
		for (int i = 0; i < list.getLength(); i++) {
 
                   Node node = list.item(i);
 
		   // get the salary element, and update the value
		   if ("tipo".equals(node.getNodeName())) {
			node.setTextContent("Luchein");
		   }
		   if ("nome".equals(node.getNodeName())) {
				node.setTextContent("si");
			   }
		   if ("server".equals(node.getNodeName())) {
				node.setTextContent("pop");
			   }
		   if ("porta".equals(node.getNodeName())) {
				node.setTextContent("nu ");
			   }
		   if ("username".equals(node.getNodeName())) {
				node.setTextContent("grandessm");
			   }
		   if ("password".equals(node.getNodeName())) {
				node.setTextContent("cddaun");
			   }
		}
 
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filepath));
		transformer.transform(source, result);
 
		System.out.println("Done");
 
	   } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	   } catch (TransformerException tfe) {
		tfe.printStackTrace();
	   } catch (IOException ioe) {
		ioe.printStackTrace();
	   } catch (SAXException sae) {
		sae.printStackTrace();
	   }
		return false;
	}


	public static void main(String[] args) throws Exception {
	    new XmlConfigurazioneDAO().creaConfigurazione(null);
	}
}