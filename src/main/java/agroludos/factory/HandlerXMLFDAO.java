package agroludos.factory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class HandlerXMLFDAO extends HandlerXML{
	private boolean fileXML;
	private boolean classXML;
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("file")) {
			if(attributes.getValue(0).equals(this.mainData))
				this.fileXML = true;
		}
		
		if (qName.equalsIgnoreCase("classe")) {
			if(this.fileXML)
				this.classXML = true;
		}
		
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.fileXML = false;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (this.fileXML && this.classXML) {
			this.res = new String(ch, start, length);
			System.out.println("Classe RequestContext da istanziare : " + res);
		}

	}
}
