package agroludos.presentation.controller.mapper;

import java.util.List;

import agroludos.business.bd.AgroludosBD;
import agroludos.business.bd.BusinessDelegate;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.req.DataFieldException;
import agroludos.presentation.reqh.DataRequestContext;
import agroludos.presentation.reqh.EmptyRequestContext;
import agroludos.to.AgroludosTO;
import agroludos.to.DatabaseTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.ManagerDiSistemaTO;
import agroludos.to.TOFactory;
import agroludos.to.UtenteTO;

class MainCommandMap{

	private AgroludosBD agroBD;

	private TOFactory toFact;

	public MainCommandMap(){
		System.out.println("MainCommandMap");
	}

	public static void chiudiAgroludos(){
		System.exit(0);
	}

	public boolean checkConfigurazione(EmptyRequestContext request){
		boolean res = false;
		System.out.println(request.getCommandName());

		Object obj = null;

//		try {
//			obj = gestisciServizio(request.getCommandName());
//			res = cast(obj, Boolean.class);
//		} catch (ApplicationException e) {
//			e.printStackTrace();
//		}

		return res;
	}

	public boolean confermaConfigurazione(DataRequestContext request){
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
			obj = (Boolean)gestisciServizio(request.getCommandName(), dbto);
			res = cast(obj, Boolean.class);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean nuovoManagerDiSistema(DataRequestContext request){
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
			obj = gestisciServizio(request.getCommandName(), mnsto);
			res = cast(obj, Boolean.class);
		} catch (DataFieldException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return res;
	}

	public UtenteTO autenticazioneUtente(DataRequestContext request){
		Object obj = null;
		UtenteTO uto = this.toFact.createUTO();

		try {
			uto.setUsername((String)request.getData("username"));
			uto.setPassword((String)request.getData("password"));
		} catch (DataFieldException e) {
			e.printStackTrace();
		}

		try {
			obj = gestisciServizio(request.getCommandName(), uto);
			uto = cast(obj, UtenteTO.class);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return uto;
	}

	public boolean testConnessioneDB(EmptyRequestContext request){
		Object obj = null;
		boolean res = false;

		try {
			obj = gestisciServizio(request.getCommandName());
			res = cast(obj, Boolean.class);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public List<ManagerDiCompetizioneTO> getAllManagerDiCompetizione(EmptyRequestContext request){
		Object obj = null;
		List<ManagerDiCompetizioneTO> res = null;

		try {
			obj = gestisciServizio(request.getCommandName());
			res = cast(obj, List.class);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

		return res;
	}

	public boolean confermaModificaMDC(DataRequestContext request){
		ManagerDiCompetizioneTO mdcto = this.toFact.createMdCTO();

		try {
			mdcto.setNome((String)request.getData("nome"));
			mdcto.setCognome((String)request.getData("cognome"));
			mdcto.setUsername((String)request.getData("username"));
			mdcto.setEmail((String)request.getData("email"));
			gestisciServizio(request.getCommandName(), mdcto);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DataFieldException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
		return false;
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

	public void setAgroBD(AgroludosBD agroBD){
		this.agroBD = agroBD;
	}
}