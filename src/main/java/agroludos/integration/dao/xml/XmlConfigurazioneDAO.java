package agroludos.integration.dao.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.to.DatabaseTO;

public class XmlConfigurazioneDAO implements ConfigurazioneDAO{

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		String tipoDB = dbto.getTipo();
		String nomeDB = dbto.getNome();
		String serverDB = dbto.getServer();
		String portaDB = "8080";
		String usernameDB = dbto.getUsername();
		String passwordDB = dbto.getPassword();

		String filepath = "src/main/resources/xml/configurazione.xml";
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the database element by tag name directly
			Node database = doc.getElementsByTagName("database").item(0);

			// loop the database child node
			NodeList list = database.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				Node node = list.item(i);

				// get the tipo element, and update the value
				if ("tipo".equals(node.getNodeName())) {
					node.setTextContent(tipoDB);
				}
				if ("nome".equals(node.getNodeName())) {
					node.setTextContent(nomeDB);
				}
				if ("server".equals(node.getNodeName())) {
					node.setTextContent(serverDB);
				}
				if ("porta".equals(node.getNodeName())) {
					node.setTextContent(portaDB);
				}
				if ("username".equals(node.getNodeName())) {
					node.setTextContent(usernameDB);
				}
				if ("password".equals(node.getNodeName())) {
					node.setTextContent(passwordDB);
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


	//	public static void main(String[] args) throws Exception {
	//	    new XmlConfigurazioneDAO().creaConfigurazione(null);
	//	}
}