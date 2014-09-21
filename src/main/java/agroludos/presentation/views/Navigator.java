package agroludos.presentation.views;

import java.io.IOException;

import javafx.stage.Stage;
import agroludos.exceptions.ViewNotFoundException;
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
		if(!this.viewsCache.checkView(viewName))
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
				//se si tratta di un dialog imposto di nuovo l'evento in chiusura dello stage
				if(agw.isDialog())
					this.viewsCache.addEventOnStageClose(agw);
			}else{
				agw = this.viewsLoader.getView(viewName);
				this.viewsCache.addView(agw);
				//TODO Gestire l'eccezione di un controller null
				agw.getController().initializeView();
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

	public void closeVista(String viewName, String setViewName, AgroludosTO mainTO) {
		this.viewsCache.popStack(viewName);
		this.getStage(viewName).close();
		this.setVista(setViewName, mainTO);		
	}
	
	public void closeVista(String viewName) {
		this.viewsCache.popStack(viewName);
		this.getStage(viewName).close();	
	}
	
}

