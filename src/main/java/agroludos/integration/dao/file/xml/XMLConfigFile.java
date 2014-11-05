package agroludos.integration.dao.file.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.system.HibernateConf;
import agroludos.to.DatabaseTO;

class XMLConfigFile {

	private String nomeDB;
	private String serverDB;
	private String portaDB;
	private String usernameDB;
	private String passwordDB;
	private HibernateConf confData;

	XMLConfigFile(HibernateConf confData){
		this.confData = confData;
	}

	String getConfPath(){
		return this.confData.getConfPath();
	}

	boolean setHibernateDocument(Document doc, DatabaseTO dbto){

		boolean res = false;

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

					if(attrValue.equals(this.confData.getHibDriver())){
						node.setTextContent(this.confData.getDriver());
						res = true;
					}

					if(attrValue.equals(this.confData.getHibDialect())){
						node.setTextContent(this.confData.getDialect());
						res = true;
					}

					if(attrValue.equals(this.confData.getHibUsername())){
						node.setTextContent(usernameDB);
						res = true;
					}

					if(attrValue.equals(this.confData.getHibPassword())){
						node.setTextContent(passwordDB);
						res = true;
					}

					if(attrValue.equals(this.confData.getHibUrl())){
						StringBuilder sb = new StringBuilder(100);
						sb.append(this.confData.getUrl());
						sb.append(this.serverDB);
						sb.append(":");
						sb.append(this.portaDB);
						sb.append("/");
						sb.append(this.nomeDB);
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