package agroludos.presentation.controller;

import agroludos.presentation.reqresh.AgroRequestContext;
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
	
	public ApplicationController getAC(AgroRequestContext request){
		ApplicationController ac = null;
		if(request instanceof DataRequestContext) 
			ac = new AgroludosController();
		return ac;
	}
}
