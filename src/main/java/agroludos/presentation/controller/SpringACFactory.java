package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.presentation.reqresh.DataRequestContext;

public class SpringACFactory implements ACFactoryI{
	
	private static GeneralAC generalAC = new GeneralAC();
//	private static UserAC userAC;
	
	private SpringACFactory(){ }
	
	public AgroludosAC createGeneralAC(){
		return generalAC;
	}
	
	public AgroludosAC createUserAC(){
		return generalAC;
	}

	@Override
	public AgroludosAC getAC(AgroRequestContext req) {
		AgroludosAC res = null;
		if(req instanceof DataRequestContext){
			res = generalAC;
			res.setReq(req);
		}
		return res;
	}
}