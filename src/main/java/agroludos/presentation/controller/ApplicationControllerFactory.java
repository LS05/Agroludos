package agroludos.presentation.controller;

import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.reqh.DataRequestContext;

class ApplicationControllerFactory implements ACFactory{

	private static GeneralAC generalAC = new GeneralAC();
	//	private static UserAC userAC;

	private ApplicationControllerFactory(){ }

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