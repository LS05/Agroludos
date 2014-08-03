package agroludos.system;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.utility.xml.XmlUtil;

public class SystemConf {
	private Document doc;
	private XmlUtil utXml = new XmlUtil();
	private String tipoDB;
	private String path;
	
	private static SystemConf sysInst;
	
	private SystemConf(){
		this.path = "src/main/java/agroludos/system/filesystem.xml";
		this.doc = utXml.getDocument(path);
	}
	
	public static SystemConf getInstance(){
		if(sysInst == null)
			sysInst = new SystemConf();
		return sysInst;
	}
	
	public boolean setTipoDB(String tipoDB){
		boolean res = false;
		this.tipoDB = tipoDB;
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

					if(attrValue.equals("database")){
						node.setTextContent(tipoDB);
						res = true;
					}
				}
			}
		}
		
		try {
			this.utXml.writeFile(this.doc, this.path);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public String getTipoDB(){
		return this.tipoDB;
	}
	
	public String getTipoConf(){
		return "xml";
	}
	
	public String getTipoCert(){
		return "txt";
	}
}
