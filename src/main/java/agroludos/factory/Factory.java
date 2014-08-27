package agroludos.factory;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public abstract class Factory {
	HandlerXML handler;
	SAXParser saxParser;

	public Factory(String tipo){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setNamespaceAware(true);
			factory.setValidating(true);

			this.saxParser = factory.newSAXParser();
			this.saxParser.setProperty(HandlerXML.JAXP_SCHEMA_LANGUAGE, HandlerXML.W3C_XML_SCHEMA);

			this.handler = HandlerXMLFactory.getHandler(tipo);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
	}

	public String initData(String data){
		try {
			this.handler.setDataToRead(data);
			this.saxParser.parse(this.getXMLPath(), handler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.handler.getResult();
	}

	public Object getInstance(String className) throws FactoryInstantiationException{
		Object res = null;
		Class<?> c = null;
		
		try {
			System.out.println("Factory -> Classe: " + className);
			c = Class.forName(className);
			res = c.newInstance();
		} catch (ClassNotFoundException e) {
			throw new FactoryInstantiationException(e);
		} catch (InstantiationException e) {
			throw new FactoryInstantiationException(e);
		} catch (IllegalAccessException e) {
			throw new FactoryInstantiationException(e);
		}

		return res;
	}

	protected abstract String getXMLPath();

}