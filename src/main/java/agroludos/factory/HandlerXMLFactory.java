package agroludos.factory;

class HandlerXMLFactory{
	
	static HandlerXML getHandler(String tipo){
		HandlerXML hx = null;
		if(tipo.equals("richieste"))
			hx = new HandlerXMLRichieste();
		else if(tipo.equals("factory"))
			hx = new HandlerXMLAC();
		return hx;
	}
	
}
