package agroludos.presentation.controller;

import agroludos.business.bd.ApplicationException;
import agroludos.business.bd.BusinessDelegate;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.to.DatabaseTO;
import agroludos.to.TOFactory;

public class GeneralAC extends ApplicationController{

	private BusinessDelegate agroBD;

	private TOFactory toFact;

	public GeneralAC(){
		System.out.println("General AC");
	}

	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}

	public void setAgroBD(BusinessDelegate agroBD){
		this.agroBD = agroBD;
	}

	public static void chiudiAgroludos()
	{
		System.exit(0);
	}

	public boolean checkConfigurazione(AgroRequestContext request){
		boolean res = false;
		System.out.println(request.getCommand());
		Object obj = agroBD.gestisciServizio(request.getCommand());
		if(obj instanceof Boolean)
			res = (Boolean)obj;
		return res;
	}

	public boolean confermaConfigurazione(AgroRequestContext request){
		System.out.println("GeneralAC.confermaConfigurazione");
		DatabaseTO dbto = this.toFact.createDatabaseTO();

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

		//		try {
		agroBD.gestisciServizio(request.getCommand(), dbto);
		//		} catch (ApplicationException e) {
		//			e.printStackTrace();
		//		}

		return false;
	}
}