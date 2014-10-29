package agroludos.presentation.views;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.to.AgroludosTO;
import agroludos.to.UtenteTO;

/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosController extends Controller{

	protected static UtenteTO utente;

	protected Properties reqProperties;

	protected Properties rulesProperties;

	public static UtenteTO getUtente(){
		return utente;
	}

	protected AgroludosController(){
		this.reqProperties = new Properties();
		this.rulesProperties = new Properties();
		Path reqPropPath = Paths.get("/properties/req.properties");
		Path errPropPath = Paths.get("/properties/validator/rules.properties");
		String reqPath = reqPropPath.toString();
		String rulesPath = errPropPath.toString();

		InputStream reqStream = this.getClass().getResourceAsStream(reqPath);
		InputStream rulesStream = this.getClass().getResourceAsStream(rulesPath);

		try {
			if(reqStream != null){
				this.reqProperties.load(reqStream);
			}

			if(reqStream != null){
				this.rulesProperties.load(rulesStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected AgroRequest getRichiesta(String commandName, String viewName){
		String richiesta = this.reqProperties.getProperty(commandName);
		return reqFact.createSimpleRequest(richiesta, viewName);		
	}

	protected AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		String richiesta = this.reqProperties.getProperty(commandName);
		return reqFact.createDataRequest(param, richiesta, viewName);
	}

	protected String getCommandName(String cmdName){
		return this.reqProperties.getProperty(cmdName);
	}

	/**
	 * Se è un dialog con un bottone tipo "Chiudi" o "Annulla"
	 * deve essere necessariamente chiamato questo metodo
	 * altrimenti non è necessario
	 */
	protected void close(){
		nav.closeVista(getViewName());
	}

	protected abstract void initializeView(AgroludosTO mainTO);

	protected abstract void initializeView(String viewName);

	protected abstract String getViewName();

	/**
	 * Per i dialog il forward è utile solo in caso di errore. Perchè il dato
	 * viene gestito sempre dalla mainView.
	 *  
	 * @param request
	 * @param response
	 */
	public abstract void forward(AgroRequest request, AgroResponse response);
}