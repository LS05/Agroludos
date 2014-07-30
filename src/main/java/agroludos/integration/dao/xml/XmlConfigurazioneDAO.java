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
import agroludos.to.DatabaseTO;

public class XmlConfigurazioneDAO implements ConfigurazioneDAO{

	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;

	private String confPath = XmlUtil.getConfPath();


	
	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {

		Document doc = this.getDocument(this.confPath);

		ScriviXML.scriviFileXML(doc, dbto);
		
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

//	public static void main(String[] args) throws Exception {
//		DatabaseTO dbto = new Database();
//		dbto.setTipo("mysql");
//		dbto.setNome("agroludos");
//		dbto.setUsername("root");
//		dbto.setPassword("root");
//		dbto.setPorta("3306");
//		dbto.setServer("localhost");
//		new XmlConfigurazioneDAO().creaConfigurazione(dbto);
//	}
}