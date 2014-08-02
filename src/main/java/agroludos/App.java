package agroludos;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
	
	private ResourceBundle itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
	
//	public String getFileFXML() {
//		return fileFXML;
//	}
//
//	public void setFileFXML(String fileFXML) {
//		this.fileFXML = fileFXML;
//	}

	@Override
	public void start(Stage stage) {
		//check configurazione
        String pathFXML="/agroludos/presentation/views/amministratore/conf_sistema.fxml";
		Parent root = null;

		try {
			root = (Parent)FXMLLoader.load(getClass().getResource(pathFXML), itBundle);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		
		stage.setTitle("Agroludos");
		stage.setScene(scene);
		stage.show();
	}
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        launch(args);
    }
}
