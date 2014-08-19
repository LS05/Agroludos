package agroludos.main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import agroludos.presentation.fc.FrontController;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.RequestFactory;
import agroludos.presentation.views.Navigator;
import agroludos.presentation.views.main.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

class AppConfig implements App{

	private ResourceBundle itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));

	private Stage stage;
	
	private Scene scene;
	
	private FrontController frontController;
	
	private RequestFactory reqFact;
	
	private Navigator nav;
	
	private boolean isConf;
	
	AppConfig(FrontController frontController, RequestFactory reqFact){
		this.frontController = frontController;
		this.reqFact = reqFact;
	}
	
	@Override
	public void initialize(Stage stage){
		this.stage = stage;
		AgroRequest richiesta = reqFact.createSimpleRequest("checkConfigurazione");
		this.isConf = (Boolean)this.frontController.eseguiRichiesta(richiesta);
	}
	
	@Override
	public void show(){
		FXMLLoader loader = null;
		Pane root = null;
		
		try {
			loader = new FXMLLoader(getClass().getResource(Navigator.MAIN), itBundle);
			root = (Pane)loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.scene = new Scene(root);
		this.stage.setScene(this.scene);
		
		MainController mainController = loader.getController();
		this.nav.setMainController(mainController);
		
		if(this.isConf){
			this.nav.loadVista(Navigator.VISTA_LOGIN);
		}else {
			this.nav.loadVista(Navigator.VISTA_CONF);
		}
		
		this.stage.show();
	}
	
	public void setNav(Navigator nav) {
		this.nav = nav;
	}
}