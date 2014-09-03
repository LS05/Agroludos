package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import javafx.stage.Stage;

public class AppConfig extends AgroludosController implements App{
	
	private Stage stage;
	
	private boolean isConf;
	
	private boolean dbConn;
	
	private AgroRequest richiesta;
	
	private AgroResponse risposta;
	
	@Override
	public void initialize(Stage stage){
		this.stage = stage;
		nav.setStage(stage);
		this.richiesta = reqFact.createRequest("checkConfigurazione");
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(richiesta, risposta);
	}
	
	@Override
	public void show(){
		this.richiesta = reqFact.createRequest("testConnessioneDB");
		this.risposta = respFact.createResponse();
		
		if(this.isConf){
			frontController.eseguiRichiesta(richiesta, risposta);
			
			if(this.dbConn){
				nav.setVista("autenticazione");
			} else {
				System.out.println("Connessione Database Non Riuscita");
			}
			
		}else {
			nav.setVista("configurazione");
		}
		
		this.stage.show();
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("checkConfigurazione")){
			this.isConf = (Boolean)response.getRespData();
		}
		
		if(request.getCommandName().equals("testConnessioneDB")){
			this.dbConn = (Boolean)response.getRespData();
		}
	}
}