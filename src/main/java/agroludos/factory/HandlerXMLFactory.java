package agroludos.factory;

class HandlerXMLFactory{
	
	static HandlerXML getHandler(String tipo){
		HandlerXML hx = null;
		if(tipo.equals("finestre")) 
			hx = new HandlerXMLViews();
		else if(tipo.equals("richieste"))
			hx = new HandlerXMLRichieste();
		return hx;
	}
	
}
