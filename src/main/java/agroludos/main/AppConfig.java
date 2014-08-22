package agroludos.main;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.views.Navigator;

import javafx.stage.Stage;

class AppConfig implements App{
	
	private FrontController frontController;
	
	private RequestFactory reqFact;
	
	private Navigator nav;
	
	private Stage stage;
	
	private boolean isConf;
	
	AppConfig(FrontController frontController, RequestFactory reqFact){
		this.frontController = frontController;
		this.reqFact = reqFact;
	}
	
	@Override
	public void initialize(Stage stage){
		this.stage = stage;
		this.nav.setStage(stage);
		AgroRequest richiesta = reqFact.createSimpleRequest("checkConfigurazione");
		this.isConf = (Boolean)this.frontController.eseguiRichiesta(richiesta);
	}
	
	@Override
	public void show(){
		boolean dbConn;
		AgroRequest richiesta = reqFact.createSimpleRequest("testConnessioneDB");
		
		if(this.isConf){
			dbConn = (Boolean)this.frontController.eseguiRichiesta(richiesta);
			
			if(dbConn){
				this.nav.setVista("login");
			} else {
				System.out.println("Connessione Database Non Riuscita");
			}
			
		}else {
			this.nav.setVista("configurazione");
		}
		
		this.stage.show();
	}
	
	public void setNav(Navigator nav) {
		this.nav = nav;
	}
}