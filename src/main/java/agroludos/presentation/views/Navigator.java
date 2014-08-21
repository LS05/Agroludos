package agroludos.presentation.views;

import javafx.scene.Scene;
import javafx.stage.Stage;

import agroludos.presentation.views.utility.PositionHandler;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class Navigator {
	
	private Stage mainStage;
	
	private ViewsLoader viewsLoader;
	
	Navigator(ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;	
	}
	
	public void setStage(Stage stage){
		this.mainStage = stage;
	}

	public void setVista(String vista) {
		Scene view = this.viewsLoader.getView(vista);
		this.mainStage.setScene(view);
		PositionHandler.centerComp(this.mainStage, view);
	}
}