package agroludos.presentation.views;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import agroludos.exceptions.ViewNotFoundException;
import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

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

	private void setViewProperties(String viewName, AgroludosWindow agw){
		Scene scene = this.viewsCache.getScene(viewName);

		if(agw.isDialog()){
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setScene(scene);
			stage.setTitle(agw.getTitle());
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(this.mainStage);
			stage.show();

			PositionHandler.centerComp(stage, scene);
		} else {
			this.mainStage.setScene(scene);
			this.mainStage.setTitle(agw.getTitle());

			PositionHandler.centerComp(this.mainStage, scene);
		}
	}

	public void setVista(String viewName) {
		AgroludosWindow agw = null;
		try {
			agw = this.viewsLoader.getView(viewName);
			this.viewsCache.addScene(agw);
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setViewProperties(viewName, agw);
	}

	public void setVista(String viewName, AgroludosTO mainTO) {
		AgroludosWindow agw = null;
		try {
			agw = this.viewsLoader.getView(viewName);

			this.viewsCache.addScene(agw, mainTO);
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setViewProperties(viewName, agw);
	}

	public AgroludosController getRequestDispatcher(String viewName) throws ViewNotFoundException, IOException{
		this.setVista(viewName);
		AgroludosController controller = null;

		try {
			controller = this.viewsLoader.getView(viewName).getController();
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controller;
	}
}