package agroludos.main;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import javafx.stage.Stage;

public class AppConfig extends AgroludosController implements App{
	
	private Stage stage;
	
	private boolean isConf;
	
	private boolean dbConn;
	
	@Override
	public void initialize(Stage stage){
		this.stage = stage;
		nav.setStage(stage);
		AgroRequest richiesta = reqFact.createRequest("checkConfigurazione");
		AgroResponse risposta = respFact.createResponse();
		frontController.eseguiRichiesta(richiesta, risposta);
	}
	
	@Override
	public void show(){
		AgroRequest richiesta = reqFact.createRequest("testConnessioneDB");
		AgroResponse risposta = respFact.createResponse();
		
		if(this.isConf){
			frontController.eseguiRichiesta(richiesta, risposta);
			
			if(dbConn){
				nav.setVista("login");
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