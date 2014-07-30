package agroludos.integration.dao.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.to.DatabaseTO;

class ScriviXML {
	
	public ScriviXML(){
		
	}

	static boolean scriviFileXML (Document doc, DatabaseTO dbto){
		
		String tipoDB = dbto.getTipo();
		String nomeDB = dbto.getNome();
		String serverDB = dbto.getServer();
		String portaDB = dbto.getPorta();
		String usernameDB = dbto.getUsername();
		String passwordDB = dbto.getPassword();

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
		
		return false;
	}
}
