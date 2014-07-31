package agroludos.integration.dao.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.to.DatabaseTO;

class XMLConfigFile {

	private String tipoDB;
	private String nomeDB;
	private String serverDB;
	private String portaDB;
	private String usernameDB;
	private String passwordDB;

	private static XMLConfigFile scriviInst;

	private XMLConfigFile(){

	}

	static XMLConfigFile getInstance(){
		if(scriviInst == null)
			scriviInst = new XMLConfigFile();
		return scriviInst;
	}

	boolean scriviFileXML (Document doc, DatabaseTO dbto){

		boolean res = false;

		this.tipoDB = dbto.getTipo();
		this.nomeDB = dbto.getNome();
		this.serverDB = dbto.getServer();
		this.portaDB = dbto.getPorta();
		this.usernameDB = dbto.getUsername();
		this.passwordDB = dbto.getPassword();	

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
						res = true;
					}

					if(attrValue.equals(XmlUtil.hibDialect)){
						node.setTextContent(XmlUtil.getDialect(tipoDB));
						res = true;
					}

					if(attrValue.equals(XmlUtil.hibUsername)){
						node.setTextContent(usernameDB);
						res = true;
					}

					if(attrValue.equals(XmlUtil.hibPassword)){
						node.setTextContent(passwordDB);
						res = true;
					}

					if(attrValue.equals(XmlUtil.hibUrl)){
						String urlDB = XmlUtil.getUrl(tipoDB) + serverDB + ":" + portaDB + "/" + nomeDB;
						node.setTextContent(urlDB);
						res = true;
					}
				}
			}
		}

		return res;
	}

}
