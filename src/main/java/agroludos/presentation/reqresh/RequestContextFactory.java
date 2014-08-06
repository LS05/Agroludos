package agroludos.presentation.reqresh;

import agroludos.factory.Factory;
import agroludos.factory.FactoryInstantiationException;
import agroludos.presentation.req.AgroRequest;

public class RequestContextFactory extends Factory{

	RequestContextFactory(){ 
		super("richieste");
	}

	public AgroRequestContext createRequestContext(AgroRequest request) {
		
		AgroRequestContext requestContext = null;

		try {
			String command = request.getCommand();
			// Identify POJO RequestContext Class for the given Command,
			// using CommandMap
			requestContext = getContextObjectClass(command);
			// Set Protcol-specific Request object
			requestContext.initialize(request);
		} catch (FactoryInstantiationException e) {
			e.printStackTrace();
		}

		return requestContext;
	}

	private AgroRequestContext getContextObjectClass(String tipo) throws FactoryInstantiationException{
		AgroRequestContext res = null;
		Object obj = this.getInstance(this.initData(tipo));
		if(obj instanceof AgroRequestContext)
			res = (AgroRequestContext)obj;
		return res;
	}

	@Override
	protected String getXMLPath() {
		return this.getClass().getResource("ReqFactory.xml").toString();
	}
}
