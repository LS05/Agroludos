package agroludos.presentation.reqresh;


import agroludos.factory.Factory;
import agroludos.presentation.req.AgroRequest;

public class RequestContextFactory extends Factory{
	private static RequestContextFactory reqFactInstance;

	private RequestContextFactory(){ 
		super("richieste");
	}

	public static RequestContextFactory getInstance(){
		if(reqFactInstance == null)
			reqFactInstance = new RequestContextFactory();
		return reqFactInstance;
	}

	public AgroRequestContext createRequestContext(AgroRequest request) {
		AgroRequestContext requestContext = null;

		try {

			String command = request.getCommand();

			// Identify POJO RequestContext Class for the given Command,
			// using CommandMap
			Class<? extends AgroRequestContext> requestContextClass = getContextObjectClass(command);

			// Instantiate POJO

			requestContext  = requestContextClass.newInstance();


			// Set Protcol-specific Request object
			requestContext.initialize(request);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return requestContext;
	}

	Class<? extends AgroRequestContext> getContextObjectClass(String tipo){
		Class res = null;
		
		try {
			String nomeClasseRichiesta = this.initData(tipo);
			res = Class.forName("agroludos.presentation.reqresh." + nomeClasseRichiesta);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	protected String getXMLPath() {
		return this.getClass().getResource("ReqFactory.xml").toString();
	}
}
