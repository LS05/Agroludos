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
			//TODO non dovrebbe farlo una volta sola? quindi controllare se è già stato inserito
			this.mainStage.setScene(scene);
			this.mainStage.setTitle(agw.getTitle());

			if(viewName.equals("initView")){
				this.mainStage.hide();
			}else{				
				this.mainStage.show();
			}
			//PositionHandler.centerComp(this.mainStage, scene);
		}
	}

	//le proprietà di una view le chiama una volta sola
	public void setVista(String viewName) {
		AgroludosWindow agw = null;
		boolean res = false;
		try {
			agw = this.viewsLoader.getView(viewName);
			res = this.viewsCache.addScene(agw);
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res)
			this.setViewProperties(viewName, agw);
	}

	//le proprietà di una view le chiama una volta sola
	//TODO controllare la gestione delle view e dei dialog
	//se le view non vengono ne reinizializzate ne vengono reinizializzate le proprietà
	//allora non avrà un to
	public void setVista(String viewName, AgroludosTO mainTO) {
		AgroludosWindow agw = null;
		boolean res = false;
		try {
			agw = this.viewsLoader.getView(viewName);

			res = this.viewsCache.addScene(agw, mainTO);
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res)
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