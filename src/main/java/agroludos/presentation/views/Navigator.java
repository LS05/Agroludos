package agroludos.presentation.views;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

	private void setVista(String viewName) {
		AgroludosWindow agw = this.viewsLoader.getView(viewName);
		this.viewsCache.addScene(agw);
		
		Scene scene = this.viewsCache.getScene(viewName);
		
		this.mainStage.setScene(scene);
		this.mainStage.setTitle(agw.getTitle());
		this.mainStage.setHeight(agw.getHeight());
		this.mainStage.setWidth(agw.getWidth());

		if(agw.isDialog()){
			Stage s = new Stage(StageStyle.UNDECORATED);
			s.setScene(scene);
			s.setTitle(agw.getTitle());
			s.setHeight(agw.getHeight());
			s.setWidth(agw.getWidth());
		    s.initModality(Modality.WINDOW_MODAL);
		    s.initOwner(this.mainStage);
			s.show();
			PositionHandler.centerComp(s, scene);
		} else {
			PositionHandler.centerComp(this.mainStage, scene);
		}
	}
	
	public AgroludosController getRequestDispatcher(String viewName){
		this.setVista(viewName);
		return this.viewsLoader.getView(viewName).getController();
	}
}