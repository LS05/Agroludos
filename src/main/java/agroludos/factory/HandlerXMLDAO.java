package agroludos.factory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class HandlerXMLDAO extends HandlerXML{
	private boolean databaseXML;
	private boolean classXML;
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("database")) {
			if(attributes.getValue(0).equals(this.mainData))
				this.databaseXML = true;
		}
		
		if (qName.equalsIgnoreCase("classe")) {
			if(this.databaseXML)
				this.classXML = true;
		}
		
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.databaseXML = false;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (this.databaseXML && this.classXML) {
			this.res = new String(ch, start, length);
			System.out.println("Classe RequestContext da istanziare : " + res);
		}

	}
}
