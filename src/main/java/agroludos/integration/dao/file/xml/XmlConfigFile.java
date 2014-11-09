package agroludos.integration.dao.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.system.HibernateConf;
import agroludos.to.DatabaseTO;

class XmlConfigFile {

	private static String nomeDB;
	private static String serverDB;
	private static String portaDB;
	private static String usernameDB;
	private static String passwordDB;

	static boolean setHibernateDocument(Document doc, HibernateConf confData, DatabaseTO dbto){

		boolean res = false;

		nomeDB = dbto.getNome();
		serverDB = dbto.getServer();
		portaDB = dbto.getPorta();
		usernameDB = dbto.getUsername();
		passwordDB = dbto.getPassword();	

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

					if(attrValue.equals(confData.getHibDriver())){
						node.setTextContent(confData.getDriver());
						res = true;
					}

					if(attrValue.equals(confData.getHibDialect())){
						node.setTextContent(confData.getDialect());
						res = true;
					}

					if(attrValue.equals(confData.getHibUsername())){
						node.setTextContent(usernameDB);
						res = true;
					}

					if(attrValue.equals(confData.getHibPassword())){
						node.setTextContent(passwordDB);
						res = true;
					}

					if(attrValue.equals(confData.getHibUrl())){
						StringBuilder sb = new StringBuilder(100);
						sb.append(confData.getUrl());
						sb.append(serverDB);
						sb.append(":");
						sb.append(portaDB);
						sb.append("/");
						sb.append(nomeDB);
						String urlDB = sb.toString();
						node.setTextContent(urlDB);
						res = true;
					}
				}
			}
		}

		return res;
	}
}