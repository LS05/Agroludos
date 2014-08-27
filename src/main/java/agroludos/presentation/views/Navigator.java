package agroludos.presentation.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import agroludos.exceptions.ViewLoadingException;
import agroludos.presentation.views.utility.PositionHandler;

public class Navigator {
//	private AgroludosStage agroStage;
	
	private Stage mainStage;
	
	private ViewsLoader viewsLoader;
	
	Navigator(ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;	
	}
	
	public void setStage(Stage stage){
		this.mainStage = stage;
	}

	public void setVista(String vista) {
		FXMLLoader loader = this.viewsLoader.getLoader(vista);
		Pane root = null;
		
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new ViewLoadingException(e.getMessage(), e.getCause());
		}
		
		Scene view = new Scene(root); 
		this.mainStage.setScene(view);
		PositionHandler.centerComp(this.mainStage, view);
	}
}