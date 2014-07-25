package agroludos.factory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class HandlerXMLRichieste extends HandlerXML{
	private boolean richiestaXML;
	private boolean classXML;
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("richiesta")) {
			if(attributes.getValue(0).equals(this.mainData))
				this.richiestaXML = true;
		}
		
		if (qName.equalsIgnoreCase("classe")) {
			if(this.richiestaXML)
				this.classXML = true;
		}
		
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.richiestaXML = false;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (this.richiestaXML && this.classXML) {
			this.res = new String(ch, start, length);
			System.out.println("Classe RequestContext da istanziare : " + res);
		}

	}

}