package agroludos.presentation.views;

import java.io.IOException;

import javafx.stage.Stage;
import agroludos.exceptions.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

public class Navigator {

	private ViewsLoader viewsLoader;

	private ViewsCache viewsCache;

	private String mainViewName;

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
				//se si tratta di un dialog richiamo l'inizializzazione
				if(agw.isDialog())					
					agw.getController().initializeView(viewName);
			}else{
				agw = this.viewsLoader.getView(viewName);
				this.viewsCache.addView(agw);
				if(agw.isMainView())
					this.mainViewName = agw.getName();
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
		if(agw.isMainView()){
			System.out.println("termina programma");
		}
		this.getStage(viewName).close();	
	}

}

