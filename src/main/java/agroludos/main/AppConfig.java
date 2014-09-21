package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.Controller;
import javafx.stage.Stage;

/**
 * La classe rappresenta l'implementazione dell'interfaccia {@link agroludos.main.App}.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 */

class AppConfig extends Controller implements App{
	private final String fromName = "mainController";
	@Override
	public void initialize(Stage stage){
		AgroRequest richiesta = reqFact.createSimpleRequest("checkConfigurazione", this.fromName);
		AgroResponse risposta = respFact.createResponse();
		
		nav.setMainStage(stage);
		nav.setVista("initView");
		frontController.eseguiRichiesta(richiesta, risposta);	
	}
}