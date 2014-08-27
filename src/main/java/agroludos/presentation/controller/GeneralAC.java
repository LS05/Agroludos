package agroludos.presentation.controller;

import java.util.List;

import agroludos.business.bd.BusinessDelegate;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.to.AgroludosTO;
import agroludos.to.DatabaseTO;
import agroludos.to.ManagerDiCompetizioneTO;
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

		Object obj = null;
		
		try {
			obj = gestisciServizio(request.getCommand());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		res = cast(obj, Boolean.class);

		return res;
	}

	public boolean confermaConfigurazione(AgroRequestContext request){
		boolean res = false;
		Object obj = null;
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

		try {
			obj = (Boolean)gestisciServizio(request.getCommand(), dbto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		res = cast(obj, Boolean.class);

		return res;
	}

	public boolean nuovoManagerDiSistema(AgroRequestContext request){
		boolean res = false;
		Object obj = null;
		
		System.out.println("GeneralAC.nuovoManagerDiSistema");
		ManagerDiSistemaTO mnsto = this.toFact.createMdSTO();

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

		try {
			obj = gestisciServizio(request.getCommand(), mnsto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		res = cast(obj, Boolean.class);
		
		return res;
	}

	public UtenteTO autenticazioneUtente(AgroRequestContext request){
		Object obj = null;
		UtenteTO uto = this.toFact.createUTO();
		
		try {
			uto.setUsername((String)request.getData("username"));
			uto.setPassword((String)request.getData("password"));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}

		try {
			obj = gestisciServizio(request.getCommand(), uto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		uto = cast(obj, UtenteTO.class);
		
		return uto;
	}

	public boolean testConnessioneDB(AgroRequestContext request){
		Object obj = null;
		boolean res = false;
		
		try {
			obj = gestisciServizio(request.getCommand());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		res = cast(obj, Boolean.class);
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione(AgroRequestContext request){
		Object obj = null;
		List<ManagerDiCompetizioneTO> res = null;
		
		try {
			obj = gestisciServizio(request.getCommand());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		res = cast(obj, List.class);
		
		return res;
	}

	private Object gestisciServizio(String command) throws ApplicationException{
		return agroBD.gestisciServizio(command);
	}

	private Object gestisciServizio(String command, AgroludosTO to) throws ApplicationException{
		return agroBD.gestisciServizio(command, to);
	}
	
	private <T> T cast(Object o, Class<T> c) {
	    try {
	        return c.cast(o);
	    } catch(ClassCastException e) {
	        return null;
	    }
	}

	public void setToFact(TOFactory toFact) {
		this.toFact = toFact;
	}

	public void setAgroBD(BusinessDelegate agroBD){
		this.agroBD = agroBD;
	}
}