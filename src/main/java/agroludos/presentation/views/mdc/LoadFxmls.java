package agroludos.presentation.views.mdc;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class LoadFxmls {
	private ResourceBundle itBundle;
	private FXMLLoader loader;
	public LoadFxmls(String url) throws IOException {
		this.itBundle = ResourceBundle.getBundle("bundles.Agroludos", Locale.forLanguageTag("it"));
		this.loader = new FXMLLoader(this.getClass().getResource(url), itBundle);

		Pane root = null;

		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}

		Scene view = new Scene(root);
	}
	public static void main(String[] args) {

		try {
			new LoadFxmls("mdcNuovaCompetizione.fxml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
