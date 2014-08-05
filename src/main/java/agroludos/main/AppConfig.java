package agroludos.main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import agroludos.presentation.fc.FrontController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

class AppConfig implements App{   
	private ResourceBundle itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
	private String pathFXML="/agroludos/presentation/views/amministratore/conf_sistema.fxml";

	private Stage stage;
	
	private Scene scene;

	private Parent root;
	
	private FrontController frontController;

	public void setPrimaryStage(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	public void setFrontController(FrontController frontController){
		this.frontController = frontController;
	}

	public void show() {
		try {
			root = (Parent)FXMLLoader.load(getClass().getResource(pathFXML), itBundle);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		root.getStylesheets().add(STYLE_FILE);
//		root.getStyleClass().add("main-window");
		stage.setTitle("Agroludos - Configurazione Iniziale");
		scene = new Scene(root);
		stage.setScene(scene);
		stage.setOnHiding(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}
}