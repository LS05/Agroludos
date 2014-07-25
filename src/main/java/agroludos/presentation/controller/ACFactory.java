package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AdiRequestContext;
import agroludos.presentation.reqresh.DataRequestContext;

public class ACFactory {
	/*---------------- SingletonPattern ----------------*/
	private static ACFactory acFactInstance;
	
	private ACFactory(){ }
	
	public static ACFactory getInstance(){
		if(acFactInstance == null)
			acFactInstance = new ACFactory();
		return acFactInstance;
	}
	/*--------------------------------------------------*/
	
	public ApplicationController getAC(AdiRequestContext request){
		ApplicationController ac = null;
		if(request instanceof DataRequestContext) 
			ac = new ADISysController();
		return ac;
	}
}
