package agroludos.presentation.controller;

import agroludos.business.bd.BDFactory;
import agroludos.business.bd.BusinessDelegate;
import agroludos.presentation.reqresh.AgroRequestContext;

public class GeneralAC extends AgroludosAC{

	private static BusinessDelegate agroBD;
	
//	private static ServerTOFactory toFact = ServerTOFactory.getTOFactory();

	public static void avvia(){
		agroBD = BDFactory.getBD();
//		int res = agroBD.checkConfigurazione();
	}
	
	public static void chiudiAgroludos()
	{
		System.exit(0);
	}
	
	public static boolean confermaConfigurazione(){
		System.out.println("GeneralAC.confermaConfigurazione");
		return false;
	}


	@Override
	public Class getResourceClass() {
		return this.getClass();
	}

}
