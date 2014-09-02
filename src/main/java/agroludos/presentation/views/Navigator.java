package agroludos.presentation.views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import agroludos.exceptions.ViewLoadingException;
import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;

public class Navigator {
//	private AgroludosStage agroStage;
	
	private Stage mainStage;
	
	private ViewsLoader viewsLoader;
	
	private ViewsCache viewsCache;
	
	Navigator(ViewsCache viewsCache, ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;
		this.viewsCache = viewsCache;
	}
	
	public void setStage(Stage stage){
		this.mainStage = stage;
	}

	public void setVista(String viewName) {
		AgroludosWindow agw = this.viewsLoader.getView(viewName);
		this.viewsCache.addScene(agw);
		
		Scene scene = this.viewsCache.getScene(viewName);
		
		this.mainStage.setScene(scene);
		this.mainStage.setTitle(agw.getTitle());
		this.mainStage.setHeight(agw.getHeight());
		this.mainStage.setWidth(agw.getWidth());

		PositionHandler.centerComp(this.mainStage, scene);
	}
	
	public void setDialog(String dialog) {
		AgroludosWindow agw = this.viewsLoader.getDialog(dialog);
		
		FXMLLoader loader = agw.getLoader();
		Pane root = null;
		
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new ViewLoadingException(e.getMessage(), e.getCause());
		}
		
		Scene view = new Scene(root);
		Stage s = new Stage(StageStyle.UNDECORATED);
		s.setScene(view);
		s.setTitle(agw.getTitle());
		s.setHeight(agw.getHeight());
		s.setWidth(agw.getWidth());
	    s.initModality(Modality.WINDOW_MODAL);
	    s.initOwner(this.mainStage);
		s.show();
		PositionHandler.centerComp(s, view);
	}
	
	public AgroludosController getRequestDispatcher(String viewName){
		return this.viewsLoader.getView(viewName).getLoader().getController();
	}
}