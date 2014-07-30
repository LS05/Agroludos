package agroludos.integration.dao.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import agroludos.integration.dao.ConfigurazioneDAO;
import agroludos.to.Database;
import agroludos.to.DatabaseTO;

public class XmlConfigurazioneDAO implements ConfigurazioneDAO{

	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;

	private String confPath = XmlUtil.getConfPath();

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		String tipoDB = dbto.getTipo();
		String nomeDB = dbto.getNome();
		String serverDB = dbto.getServer();
		String portaDB = dbto.getPorta();
		String usernameDB = dbto.getUsername();
		String passwordDB = dbto.getPassword();

		Document doc = this.getDocument(this.confPath);

		// Get the database element by tag name directly
		Node database = doc.getElementsByTagName("session-factory").item(0);

		// loop the database child node
		NodeList list = database.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {

			Node node = list.item(i);

			if("property".equals(node.getNodeName())){

				NamedNodeMap attributes = node.getAttributes();

				for (int a = 0; a < attributes.getLength(); a++) {
					Node attr = attributes.item(a);
					String attrValue = attr.getNodeValue();

					if(attrValue.equals(XmlUtil.hibDriver)){
						node.setTextContent(XmlUtil.getDriver(tipoDB));
					}

					if(attrValue.equals(XmlUtil.hibDialect)){
						node.setTextContent(XmlUtil.getDialect(tipoDB));
					}

					if(attrValue.equals(XmlUtil.hibUsername)){
						node.setTextContent(usernameDB);
					}

					if(attrValue.equals(XmlUtil.hibPassword)){
						node.setTextContent(passwordDB);
					}

					if(attrValue.equals(XmlUtil.hibUrl)){
						String urlDB = XmlUtil.getUrl(tipoDB) + serverDB + ":" + portaDB + "/" + nomeDB;
						node.setTextContent(urlDB);
					}
				}
			}
		}

		// write the content into xml file
		try {
			this.writeFile(doc, this.confPath);

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	private Document getDocument(String path){
		Document doc = null;

		this.docFactory = DocumentBuilderFactory.newInstance();

		try {
			this.docBuilder = docFactory.newDocumentBuilder();
			doc = this.docBuilder.parse(path);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return doc;
	}

	private void writeFile(Document doc, String path) throws TransformerException{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		
		DocumentType doctype = doc.getDoctype();
		
        if(doctype != null) {
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        }
        
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));
		transformer.transform(source, result);
	}

	public static void main(String[] args) throws Exception {
		DatabaseTO dbto = new Database();
		dbto.setTipo("mysql");
		dbto.setNome("agroludos");
		dbto.setUsername("root");
		dbto.setPassword("root");
		dbto.setPorta("3306");
		dbto.setServer("localhost");
		new XmlConfigurazioneDAO().creaConfigurazione(dbto);
	}
}