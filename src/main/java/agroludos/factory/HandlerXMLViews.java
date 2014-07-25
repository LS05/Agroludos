package agroludos.factory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class HandlerXMLViews extends HandlerXML{
	private boolean finestraXML;
	private boolean classXML;

	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("finestra")) {
			if(attributes.getValue(1).equals(mainData))
				this.finestraXML = true;
		}

		if (qName.equalsIgnoreCase("classe")) {
			if(this.finestraXML)
				this.classXML = true;
		}

	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.finestraXML = false;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (this.finestraXML && this.classXML) {
			this.res = new String(ch, start, length);
			System.out.println("Classe Finestra da istanziare : " + res);
		}

	}
	
}
