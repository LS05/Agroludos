package agroludos.presentation.views;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;

public class Navigator {
	
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

		if(agw.isDialog()){
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.setTitle(agw.getTitle());
			stage.setHeight(agw.getHeight());
			stage.setWidth(agw.getWidth());
		    stage.initModality(Modality.WINDOW_MODAL);
		    stage.initOwner(this.mainStage);
			stage.show();
			
			PositionHandler.centerComp(stage, scene);
		} else {
			this.mainStage.setScene(scene);
			this.mainStage.setTitle(agw.getTitle());
			this.mainStage.setHeight(agw.getHeight());
			this.mainStage.setWidth(agw.getWidth());
			
			PositionHandler.centerComp(this.mainStage, scene);
		}
	}
	
	public AgroludosController getRequestDispatcher(String viewName){
		this.setVista(viewName);
		return this.viewsLoader.getView(viewName).getController();
	}
}