package agroludos.presentation.controller;

import agroludos.business.bd.ApplicationException;
import agroludos.business.bd.BDFactory;
import agroludos.business.bd.BusinessDelegate;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqresh.AgroRequestContext;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

public class GeneralAC extends AgroludosAC{

	private static BusinessDelegate agroBD;
	
	GeneralAC(){
		super();
		agroBD = BDFactory.getBD();
	}
	
	public static void chiudiAgroludos()
	{
		System.exit(0);
	}
	
	public static boolean confermaConfigurazione(AgroRequestContext request){
		System.out.println("GeneralAC.confermaConfigurazione");
		DatabaseTO dbto = TOFactory.getDatabaseTO();
		
		try {
			dbto.setTipo((String)(request.getData((String)"tipo")));
			dbto.setNome((String)(request.getData((String)"nome")));
			dbto.setServer((String)(request.getData((String)"server")));
			dbto.setPorta((String)(request.getData((String)"porta")));
			dbto.setUsername((String)(request.getData((String)"username")));
			dbto.setPassword((String)(request.getData((String)"password")));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}
		
		try {
			agroBD.creaConfigurazione(dbto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Class<? extends GeneralAC> getResourceClass() {
		return this.getClass();
	}

}
