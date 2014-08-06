package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.presentation.reqresh.DataRequestContext;

public class SpringACFactory implements ACFactoryI{
	
	private AgroRequestContext req;
	
	private static AgroludosAC generalAC = new GeneralAC();
	
	private SpringACFactory(){ }
	
	@Override
	public AgroludosAC getAC(){
		AgroludosAC res = null;
		if(this.req instanceof DataRequestContext)
			res = generalAC;
		return res;
	}
	
	@Override
	public void setAgroRequestContext(AgroRequestContext req) {
		this.req = req;
	}
}
