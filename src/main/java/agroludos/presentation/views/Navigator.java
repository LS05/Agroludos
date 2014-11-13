package agroludos.presentation.views;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import agroludos.exceptions.system.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

public class Navigator {

	private ViewsLoader viewsLoader;

	private ViewsCache viewsCache;

	Navigator(ViewsCache viewsCache, ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;
		this.viewsCache = viewsCache;
	}

	public void setMainStage(Stage mainStage) {
		this.viewsCache.addMainStage(mainStage);
	}

	public AgroludosController getRequestDispatcher(String viewName) throws ViewNotFoundException, IOException{
		if(!this.viewsCache.checkView(viewName)){
			this.setVista(viewName);
		}

		AgroludosController controller = null;

		try {
			controller = this.viewsLoader.getView(viewName).getController();
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return controller;
	}

	public Stage getStage(String viewName){
		return this.viewsCache.getView(viewName).getStage();
	}

	public void setVista(String viewName, AgroludosTO mainTO){
		this.setVista(viewName);
		this.viewsCache.getView(viewName).getController().initializeView(mainTO);
	}

	public void setVista(String viewName){
		AgroludosWindow agw = null;

		try {
			if(this.viewsCache.checkView(viewName)){
				agw = this.viewsCache.getView(viewName);					
				agw.getController().initializeView(viewName);
			}else{
				agw = this.viewsLoader.getView(viewName);
				this.viewsCache.addView(agw);

				if(agw.isMainView()){
					final Stage stage = agw.getStage();
					final String mainView = viewName;
					//aggiungo l'evento close vista quando si chiude lo stage
					stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
						public void handle(WindowEvent we) {
							closeVista(mainView);
						}
					});
				}

				//TODO Gestire l'eccezione di un controller null
				agw.getController().initializeView(viewName);
			}
			if(agw.isDialog() || agw.isMainView()){
				agw.getStage().show();	
			}

		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void closeVista(String viewName) {
		AgroludosWindow agw = this.viewsCache.getView(viewName);
		if(agw != null){
			this.getStage(viewName).close();	
		}
	}
}