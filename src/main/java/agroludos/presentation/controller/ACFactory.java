package agroludos.presentation.controller;

import agroludos.factory.Factory;
import agroludos.factory.FactoryInstantiationException;
import agroludos.presentation.reqresh.AgroRequestContext;

public class ACFactory extends Factory{
	
	private ACFactory(){ 
		super("factory");
	}
	
	public AgroludosAC getAC(AgroRequestContext request){
		AgroludosAC ac = null;

		try {
			ac = getACClass(request.getClassName());
		} catch (FactoryInstantiationException e) {
			e.printStackTrace();
		}

		return ac;
	}
	
	private AgroludosAC getACClass(String tipo) throws FactoryInstantiationException{
		AgroludosAC res = null;
		Object obj = this.getInstance(this.initData(tipo));
		if(obj instanceof AgroludosAC)
			res = (AgroludosAC)obj;
		return res;
	}

	@Override
	protected String getXMLPath() {
		return this.getClass().getResource("ControllerFactory.xml").toString();
	}
}
