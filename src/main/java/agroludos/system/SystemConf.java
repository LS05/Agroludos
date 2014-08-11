package agroludos.system;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.utility.xml.XmlUtil;

public class SystemConf {
	private Document doc;
	private XmlUtil utXml;
	private String path;

	SystemConf(XmlUtil utXml){
		this.utXml = utXml;
		this.path = "src/main/java/agroludos/system/filesystem.xml";
		this.doc = utXml.getDocument(path);
	}

	public boolean setTipoDB(String tipoDB){
		boolean res = false;

		this.writeDataBaseNode(tipoDB);

		try {
			this.utXml.writeFile(this.doc, this.path);
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return res;
	}

	public String getTipoDB(){
		return this.readDataBaseNode();
	}

	public String getTipoConf(){
		return this.readConfNode();
	}

	public String getTipoCert(){
		return this.readCertNode();
	}

	private Node getSystemNode(String nodeName){
		Node res = null;
		Node database = doc.getElementsByTagName("agroludostypes").item(0);
		// loop the database child node
		NodeList list = database.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {

			Node node = list.item(i);

			if("type".equals(node.getNodeName())){

				NamedNodeMap attributes = node.getAttributes();

				for (int a = 0; a < attributes.getLength(); a++) {
					Node attr = attributes.item(a);
					String attrValue = attr.getNodeValue();

					if(attrValue.equals(nodeName)){
						res = node;
					}
				}
			}
		}

		return res;
	}

	private void writeDataBaseNode(String s){
		Node n = this.getSystemNode("database");
		n.setTextContent(s);
	}

	private String readDataBaseNode(){
		return this.getSystemNode("database").getTextContent();
	}

	private String readConfNode(){
		return this.getSystemNode("configurazione").getTextContent();
	}

	private String readCertNode(){
		return this.getSystemNode("certificato").getTextContent();
	}
}
