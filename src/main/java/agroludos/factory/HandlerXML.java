package agroludos.factory;

import org.xml.sax.helpers.DefaultHandler;

class HandlerXML extends DefaultHandler{
	static final String JAXP_SCHEMA_LANGUAGE =
	        "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	    
    static final String W3C_XML_SCHEMA =
        "http://www.w3.org/2001/XMLSchema";

    static final String JAXP_SCHEMA_SOURCE =
        "http://java.sun.com/xml/jaxp/properties/schemaSource";
    
	protected String mainData;
	protected String res;
	
	void reset(){
		this.mainData = "";
		this.res = "";
	}
	
	String getResult(){
		return this.res;
	}
	
	void setDataToRead(String data){
		this.mainData = data;
	}
	
}
