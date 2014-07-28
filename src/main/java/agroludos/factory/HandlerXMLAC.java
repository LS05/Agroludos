package agroludos.factory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class HandlerXMLAC extends HandlerXML{
	
	private boolean tipoRichiesta;
	private boolean classXML;
	
	public void startElement(String uri, String localName,String qName, 
			Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("context")) {
			if(attributes.getValue(0).equals(this.mainData))
				this.tipoRichiesta = true;
		}
		
		if (qName.equalsIgnoreCase("classe")) {
			if(this.tipoRichiesta)
				this.classXML = true;
		}
		
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		this.tipoRichiesta = false;
	}

	public void characters(char ch[], int start, int length) throws SAXException {

		if (this.tipoRichiesta && this.classXML) {
			this.res = new String(ch, start, length);
		}
	}

}
