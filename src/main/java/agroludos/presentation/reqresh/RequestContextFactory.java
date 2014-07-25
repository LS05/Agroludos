package agroludos.presentation.reqresh;


import adisys.server.factory.Factory;
import adisys.server.presentation.req.AdiRequest;

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

	public AdiRequestContext createRequestContext(AdiRequest request) {
		AdiRequestContext requestContext = null;

		try {

			String command = request.getCommand();

			// Identify POJO RequestContext Class for the given Command,
			// using CommandMap
			Class<? extends AdiRequestContext> requestContextClass = getContextObjectClass(command);

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

	Class<? extends AdiRequestContext> getContextObjectClass(String tipo){
		Class res = null;
		
		try {
			String nomeClasseRichiesta = this.initData(tipo);
			res = Class.forName("adisys.server.presentation.reqresh." + nomeClasseRichiesta);
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
