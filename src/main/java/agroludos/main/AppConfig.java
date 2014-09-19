package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.Controller;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * La classe rappresenta l'implementazione dell'interfaccia {@link agroludos.main.App}.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 */

class AppConfig extends Controller implements App{

	@Override
	public void initialize(Stage stage){
		AgroRequest richiesta = reqFact.createSimpleRequest("checkConfigurazione");
		AgroResponse risposta = respFact.createResponse();
		
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		stage.setWidth(bounds.getWidth());
		stage.setHeight(bounds.getHeight());
		nav.setStage(stage);
		nav.setVista("initView");
		frontController.eseguiRichiesta(richiesta, risposta);	
	}
}