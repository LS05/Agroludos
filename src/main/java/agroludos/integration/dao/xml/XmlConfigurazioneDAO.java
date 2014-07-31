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
import org.xml.sax.SAXException;

import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

public class XmlConfigurazioneDAO implements FConfigurazioneDAO{

	private DocumentBuilderFactory docFactory;
	private DocumentBuilder docBuilder;
	private Document doc;

	private String confPath = XmlUtil.getConfPath();
	
	private XMLConfigFile xmlFile;
	
	XmlConfigurazioneDAO(){
		this.doc = this.getDocument(this.confPath);
		this.xmlFile = XMLConfigFile.getInstance();
	}

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {
		
		boolean res = false;

		if(this.doc != null){
			res = xmlFile.scriviFileXML(this.doc, dbto);
			try {
				this.writeFile(this.doc, this.confPath);
			} catch (TransformerException e) {
				res = false;
				e.printStackTrace();
			}
		} else {
			res = false;
		}

		return res;
	}

	private Document getDocument(String path){
		Document doc = null;

		this.docFactory = DocumentBuilderFactory.newInstance();

		try {
			this.docBuilder = docFactory.newDocumentBuilder();
			doc = this.docBuilder.parse(path);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	@Override
	public String getConfPath() {
		return this.confPath;
	}
}