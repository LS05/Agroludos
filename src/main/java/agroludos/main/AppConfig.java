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
		
		if(this.isConf){
			this.nav.setVista("login");
		}else {
			this.nav.setVista("configurazione");
		}
		
		this.stage.show();
	}
	
	public void setNav(Navigator nav) {
		this.nav = nav;
	}
}