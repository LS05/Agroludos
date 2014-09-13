package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.Controller;
import javafx.stage.Stage;

class AppConfig extends Controller implements App{
	
	private Stage stage;
	
	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	public void initialize(Stage stage){
		this.stage = stage;
		this.stage.show();
		nav.setStage(stage);
		nav.setVista("initView");
		this.richiesta = reqFact.createSimpleRequest("checkConfigurazione");
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(richiesta, risposta);
	}
}