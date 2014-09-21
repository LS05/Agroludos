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

	@Override
	public void initialize(Stage mainStage){
		AgroRequest richiesta = reqFact.createSimpleRequest("checkConfigurazione");
		AgroResponse risposta = respFact.createResponse();
		
		nav.setMainStage(mainStage);
		nav.setVista("initView");
		frontController.eseguiRichiesta(richiesta, risposta);	
	}
}