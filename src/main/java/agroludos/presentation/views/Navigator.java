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

	/**
	 * Loads the vista specified by the fxml file into the
	 * vistaHolder pane of the main application layout.
	 *
	 * Previously loaded vista for the same fxml file are not cached.
	 * The fxml is loaded anew and a new vista node hierarchy generated
	 * every time this method is invoked.
	 *
	 * A more sophisticated load function could potentially add some
	 * enhancements or optimizations, for example:
	 *   cache FXMLLoaders
	 *   cache loaded vista nodes, so they can be recalled or reused
	 *   allow a user to specify vista node reuse or new creation
	 *   allow back and forward history like a browser
	 *
	 * @param fxml the fxml file to be loaded.
	 */
	public void setVista(String vista) {
		Scene view = this.viewsLoader.getView(vista);
		this.mainStage.setScene(view);
		PositionHandler.centerComp(this.mainStage, view);
//		this.mainController.setView(view.getRoot());
	}
}