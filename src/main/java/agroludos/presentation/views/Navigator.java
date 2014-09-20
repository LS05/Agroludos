package agroludos.presentation.views;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import agroludos.exceptions.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

public class Navigator {

	private Stage mainStage;

	private ViewsLoader viewsLoader;

	private ViewsCache viewsCache;

	private Stage currentStage;

	protected String currentViewName;

	Navigator(ViewsCache viewsCache, ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;
		this.viewsCache = viewsCache;
	}

	public void setStage(Stage stage){
		this.mainStage = stage;
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
			}else{
				agw = this.viewsLoader.getView(viewName);
				
				//Se è un dialog posizionalo sullo stage corrente
				if(agw.isDialog()){
					this.viewsCache.addView(agw,this.currentStage);
					//lo stage corrente diventa quello del dialog aggiunto
					this.currentStage = this.getStage(agw.getName());
				}
				else{
					this.currentStage = this.mainStage;
					this.viewsCache.addView(agw,this.mainStage);
				}
				//TODO Gestire l'eccezione di un controller null
				agw.getController().initializeView();
			}
			//TODO inserire tipo initView ad agroludosView
			if(!(agw.getName().equals("initView") || agw.getName().equals("session")) ){
				this.currentViewName = agw.getName();
				agw.getStage().show();
				//aggiungo l'evento close vista quando si chiude lo stage
				agw.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent we) {
						//TODO eliminare stampa
						//CompetizioneTO cmpto = (CompetizioneTO) mainTO;
						System.out.println("Stage is closing");
						closeVista(currentViewName);
					}
				}); 
			}
		} catch (ViewNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//chiamato ogni volta che si chiude uno stage
	public void closeVista(String viewName){
		AgroludosWindow agw = this.viewsCache.getView(viewName);
		//se è un dialog setta lo stage corrente con l'owner stage
		if(agw.isDialog()){
			this.currentStage = agw.getOwnerStage();
			this.currentViewName = agw.getName();
			agw.getStage().close();
		}
		else{
			//altrimenti chiudi il programma
			System.out.println("programma chiuso");
		}
	}
}

