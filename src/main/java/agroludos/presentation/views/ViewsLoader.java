package agroludos.presentation.views;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import agroludos.utility.xml.XmlUtil;

class ViewsLoader{
	private XmlUtil utXml;
	private ResourceBundle itBundle;
	private Document docViews;
	private Map<String, String> viewsPaths;
	private Map<String, FXMLLoader> views;
	
	ViewsLoader(XmlUtil utXml){
		this.utXml = utXml;
		this.docViews = this.utXml.getDocument(this.getClass().getResource("views.xml").toString());
		this.itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
		this.viewsPaths = new HashMap<String, String>();
		this.views = new HashMap<String, FXMLLoader>();
		setViewMap();
		setViews();
	}

	private void setViewMap(){
		Node database = this.docViews.getElementsByTagName("agro-views").item(0);
		NodeList list = database.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {

			Node node = list.item(i);

			if("view".equals(node.getNodeName())){

				NamedNodeMap attributes = node.getAttributes();

				for (int a = 0; a < attributes.getLength(); a++) {
					Node attr = attributes.item(a);
					String attrValue = attr.getNodeValue();
					this.viewsPaths.put(attrValue, node.getTextContent());
				}
			}
		}
	}
	
	private void setViews() {
		for (Map.Entry<String, String> entry : this.viewsPaths.entrySet()) {
			FXMLLoader loader = null;
			loader = new FXMLLoader(ViewsLoader.class.getResource(entry.getValue()), this.itBundle);
		    this.views.put(entry.getKey(), loader);
		}
	}
	
	
	FXMLLoader getLoader(String view){
		return this.views.get(view);
	}
}