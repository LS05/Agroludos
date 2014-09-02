package agroludos.factory;

import agroludos.exceptions.FactoryInstantiationException;

class HandlerXMLFactory{
	
	static HandlerXML getHandler(String tipo){
		HandlerXML hx = null;
		if(tipo.equals("richieste"))
			hx = new HandlerXMLRichieste();
		else
			throw new FactoryInstantiationException("");
		return hx;
	}
	
}
