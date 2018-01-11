package agroludos.presentation.views;


import java.io.IOException;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import agroludos.exceptions.system.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

/**
 * Navigator si occupa di effettuare il dispatching del risultato dell'esecuzione di un servizio utilizzando
 * {@link ViewsCache} per memorizzare le view
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class Navigator implements NavigatorI {

	private ViewsLoader viewsLoader;

	private ViewsCache viewsCache;

	/**
	 * inizializza le variabili viewsCache e viewsLoader
	 * @param viewsCache
	 * @param viewsLoader
	 */
	Navigator(ViewsCache viewsCache, ViewsLoader viewsLoader){
		this.viewsLoader = viewsLoader;
		this.viewsCache = viewsCache;
	}

	/**
	 * Inserisce il main stage nella viewsCache
	 * @param mainStage
	 */
	public void setMainStage(Stage mainStage) {
		this.viewsCache.addMainStage(mainStage);
	}

	/**
	 * Restituisce il controller del nome della view in input
	 * se non è stata creata la crea invocando il metodo {@link #setVista(String)}
	 * @param viewName
	 * @return
	 * @throws ViewNotFoundException
	 * @throws IOException
	 */
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

	/**
	 * Restituisce lo stage di una view tramite il suo nome interrogando {@link ViewsCache}
	 * @param viewName
	 * @return
	 */
	public Stage getStage(String viewName){
		return this.viewsCache.getView(viewName).getStage();
	}

	/**
	 * invoca il metodo {@link #setVista(String)} per mostrare la view dopodichè chiama il 
	 * metodo initializeView passando un TO che inizializzerà i dati della view
	 * @param viewName
	 * @param mainTO
	 */
	public void setVista(String viewName, AgroludosTO mainTO){
		this.setVista(viewName);
		this.viewsCache.getView(viewName).getController().initializeView(mainTO);
	}

	/**
	 * Crea o legge se già esistente una view nella {@link ViewsCache} e la mostra a video
	 * Se si tratta di una view di controllo non effettua lo show()
	 * @param viewName
	 */
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

	/**
	 * chiude una view
	 * @param viewName
	 */
	public void closeVista(String viewName) {
		AgroludosWindow agw = this.viewsCache.getView(viewName);
		if(agw != null){
			this.getStage(viewName).close();	
		}
	}
}