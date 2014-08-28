package agroludos.presentation.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import agroludos.exceptions.ViewLoadingException;
import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;

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
		AgroludosWindow agw = this.viewsLoader.getView(vista);
		
		FXMLLoader loader = agw.getLoader();
		Pane root = null;
		
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new ViewLoadingException(e.getMessage(), e.getCause());
		}
		
		Scene view = new Scene(root);
		
		this.mainStage.setScene(view);
		this.mainStage.setTitle(agw.getTitle());
		this.mainStage.setHeight(agw.getHeight());
		this.mainStage.setWidth(agw.getWidth());
		
		PositionHandler.centerComp(this.mainStage, view);
	}
	
	public void showDialog(String dialog) {
		AgroludosWindow agw = this.viewsLoader.getDialog(dialog);
		
		FXMLLoader loader = agw.getLoader();
		Pane root = null;
		
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new ViewLoadingException(e.getMessage(), e.getCause());
		}
		
		Scene view = new Scene(root);
		Stage s = new Stage();
		s.setScene(view);
		s.setTitle(agw.getTitle());
		s.setHeight(agw.getHeight());
		s.setWidth(agw.getWidth());
		s.show();
		PositionHandler.centerComp(s, view);
	}
}