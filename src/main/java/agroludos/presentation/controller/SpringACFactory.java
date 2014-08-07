package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.presentation.reqresh.DataRequestContext;

public class SpringACFactory implements ACFactoryI{
	
	private static GeneralAC generalAC = new GeneralAC();
//	private static UserAC userAC;
	
	private SpringACFactory(){ }
	
	public ApplicationController createGeneralAC(){
		return generalAC;
	}
	
	public ApplicationController createUserAC(){
		return generalAC;
	}

	@Override
	public ApplicationController getAC(AgroRequestContext req) {
		ApplicationController res = null;
		if(req instanceof DataRequestContext){
			res = generalAC;
		}
		return res;
	}
}