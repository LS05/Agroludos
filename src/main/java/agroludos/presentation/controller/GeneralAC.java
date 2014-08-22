package agroludos.presentation.controller;

import agroludos.business.bd.BusinessDelegate;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.to.AgroludosTO;
import agroludos.to.DatabaseTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

public class GeneralAC extends ApplicationController{

	private BusinessDelegate agroBD;

	private TOFactory toFact;

	public GeneralAC(){
		System.out.println("General AC");
	}

	public static void chiudiAgroludos(){
		System.exit(0);
	}

	public boolean checkConfigurazione(AgroRequestContext request){
		boolean res = false;
		System.out.println(request.getCommand());
		
		Object obj = gestisciServizio(request.getCommand());
		
		if(obj instanceof Boolean)
			res = (Boolean)obj;
		
		return res;
	}

	public boolean confermaConfigurazione(AgroRequestContext request){
		boolean res = false;
		System.out.println("GeneralAC.confermaConfigurazione");
		DatabaseTO dbto = this.toFact.createDatabaseTO();

		try {
			dbto.setTipo((String)(request.getData("tipo")));
			dbto.setNome((String)(request.getData("nome")));
			dbto.setServer((String)(request.getData("server")));
			dbto.setPorta((String)(request.getData("porta")));
			dbto.setUsername((String)(request.getData("username")));
			dbto.setPassword((String)(request.getData("password")));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}
		
		res = (Boolean)gestisciServizio(request.getCommand(), dbto);

		return res;
	}
	
	public boolean nuovoManagerDiSistema(AgroRequestContext request){
		System.out.println("GeneralAC.nuovoManagerDiSistema");
		ManagerDiSistemaTO mnsto = this.toFact.createMdSTO();
		boolean res = false;
		
		try {
			mnsto.setNome((String)request.getData("nome"));
			mnsto.setCognome((String)request.getData("cognome"));
			mnsto.setEmail((String)request.getData("email"));
			mnsto.setUsername((String)request.getData("username"));
			mnsto.setPassword((String)request.getData("password"));
			mnsto.setTelefono((String)request.getData("telefono"));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}
		
		res = (Boolean)gestisciServizio(request.getCommand(), mnsto);
		
		return res;
	}
	
	public UtenteTO autenticazioneUtente(AgroRequestContext request){
		
		UtenteTO uto = this.toFact.createUTO();
		
		try {
			uto.setUsername((String)request.getData("username"));
			uto.setPassword((String)request.getData("password"));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}
		
		return (UtenteTO)gestisciServizio(request.getCommand(), uto);
	}
	
	public boolean testConnessioneDB(AgroRequestContext request){
		return (Boolean)gestisciServizio(request.getCommand());
	}
	
	private Object gestisciServizio(String command){
		Object res = null;
		
		try {
			res = agroBD.gestisciServizio(command);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	private Object gestisciServizio(String command, AgroludosTO to){
		Object res = null;
		
		try {
			res = agroBD.gestisciServizio(command, to);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}

	public void setAgroBD(BusinessDelegate agroBD){
		this.agroBD = agroBD;
	}
}