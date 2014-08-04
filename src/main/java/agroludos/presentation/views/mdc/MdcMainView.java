package agroludos.presentation.views.mdc;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MdcMainView extends Application{

	private ResourceBundle itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));

	@Override
	public void start(Stage stage) {
		Parent root = null;

		try {
			root = (Parent)FXMLLoader.load(getClass().getResource("mdcMain.fxml"), itBundle);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);

		stage.setTitle("Agroludos");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {

		launch(args);
	}
}
