package agroludos.presentation.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;

/**
 * Rappresenta una cache contenente tutte le view
 * disponibili. Le view sono ottenute attraverso il metodo {@link #getView(String)}
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ViewsCache {

	private Map<String, AgroludosWindow> views;
	private Stage mainStage;

	/**
	 * inizializza l'hashmap
	 */
	ViewsCache(){
		this.views = new HashMap<String, AgroludosWindow>();
		this.mainStage = null;
	}

	/**
	 * Restituisce vero se esiste la view all'interno dell'hashmap
	 * @param viewName
	 * @return
	 */
	boolean checkView(String viewName) {
		return this.views.containsKey(viewName);
	}

	/**
	 * Restituisce la view tramite il suo nome
	 * @param viewName
	 * @return
	 */
	AgroludosWindow getView(String viewName) {
		return this.views.get(viewName);
	}

	/**
	 * aggiunge una view alla {@link ViewsCache}
	 * @param window
	 * @throws IOException
	 */
	void addView(AgroludosWindow window) throws IOException {
		//carico l'fxml e setto scena e stage
		FXMLLoader loader = window.getLoader();
		Pane root = null;
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}
		if(window.isDialog() || window.isMainView()){
			//posiziono la view e setto la grandezza
			double height = 0;
			double width = 0;
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();

			if(window.isDialog()){
				height = window.getHeight();
				width =  window.getWidth();
				//creo la scena		
				Scene dialogScene = new Scene(root, width, height);
				Stage dialogStage = new Stage();
				dialogStage.setScene(dialogScene);
				dialogStage.setTitle(window.getTitle());
				dialogStage.setResizable(false);
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				window.setStage(dialogStage);
				PositionHandler.centerComp(window.getStage(), window.getStage().getScene());

			}else if(window.isMainView()){
				height = bounds.getHeight();
				width =  bounds.getWidth();		
				//creo la scena	e la aggiungo al mainStage
				Scene mainViewScene = new Scene(root, width, height);
				Stage mainViewStage = new Stage(StageStyle.DECORATED);
				mainViewStage.setScene(mainViewScene);
				mainViewStage.setTitle(window.getTitle());
				//setto il mainStage
				window.setStage(mainViewStage);
			}
		}
		this.views.put(window.getName(), window);

	}

	/**
	 * setta il mainstage
	 * @param mainStage
	 */
	public void addMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	/**
	 * chiude il mainstage
	 */
	public void closeMainStage() {
		this.mainStage.close();
	}
}