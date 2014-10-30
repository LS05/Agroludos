package agroludos.presentation.views;

import java.io.IOException;
import java.io.InputStream;
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

	private static InputStream reqStream;

	private static InputStream rulesStream;

	protected static UtenteTO utente;

	protected static Properties reqProperties;

	protected static Properties rulesProperties;

	protected AgroludosController(){

		try {

			if(reqStream == null){
				reqProperties = new Properties();

				reqStream = this.getClass().getResourceAsStream("/properties/req.properties");

				if(reqStream != null){
					reqProperties.load(reqStream);
				}
			}

			if(rulesStream == null){
				rulesProperties = new Properties();

				rulesStream = this.getClass().getResourceAsStream("/properties/validator/rules.properties");

				if(rulesStream != null){
					rulesProperties.load(rulesStream);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static UtenteTO getUtente(){
		return utente;
	}

	protected AgroRequest getRichiesta(String commandName, String viewName){
		String richiesta = reqProperties.getProperty(commandName);
		return reqFact.createSimpleRequest(richiesta, viewName);		
	}

	protected AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		String richiesta = reqProperties.getProperty(commandName);
		return reqFact.createDataRequest(param, richiesta, viewName);
	}

	protected String getCommandName(String cmdName){
		return reqProperties.getProperty(cmdName);
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