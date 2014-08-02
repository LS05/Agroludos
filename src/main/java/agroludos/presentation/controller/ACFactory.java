package agroludos.presentation.controller;

import agroludos.factory.Factory;
import agroludos.presentation.reqresh.AgroRequestContext;

public class ACFactory extends Factory{

	private static ACFactory acFactInstance;
	
	private ACFactory(){ 
		super("factory");
	}
	
	public static ACFactory getInstance(){
		if(acFactInstance == null)
			acFactInstance = new ACFactory();
		return acFactInstance;
	}
	
	public AgroludosAC getAC(AgroRequestContext request){
		AgroludosAC ac = null;

		try {
			Class<? extends AgroludosAC> res = getACClass(request.getClassName());
			ac = res.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ac;
	}
	
	private Class<? extends AgroludosAC> getACClass(String tipo){
		return this.getClass("agroludos.presentation.controller.", this.initData(tipo));
	}

	@Override
	protected String getXMLPath() {
		return this.getClass().getResource("ControllerFactory.xml").toString();
	}
}
