package agroludos.presentation.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.to.AgroludosTO;

class ViewsCache {

	private Map<String, Scene> scenes;

	ViewsCache(){
		this.scenes = new HashMap<String, Scene>();
	}

	private boolean addSceneSupp(AgroludosWindow window) throws IOException{
		boolean res = false;

		if(!this.scenes.containsKey(window.getName())){
			FXMLLoader loader = window.getLoader();

			Pane root = null;

			try {
				root = (Pane)loader.load();
			} catch (IOException e) {
				throw new IOException(e.getMessage(), e.getCause());
			}

			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			
			double height = 0;
			double width = 0;
			
			if(window.isDialog()){
				height = window.getHeight();
				width =  window.getWidth();
			}else{
				height = bounds.getHeight();
				width =  bounds.getWidth();
			}
					
			Scene view = new Scene(root, width, height);
			this.scenes.put(window.getName(), view);
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	//TODO
	boolean addScene(AgroludosWindow window) throws IOException{
		boolean res = false;
		if(this.addSceneSupp(window)){
			window.getController().initializeView();
			res = true;
		}
		return res;
	}

	/**
	 * Il dialog per essere aggiornato e ricevere di nuovo un TO,
	 * ha bisogno di essere inizializzato nuovamente.
	 * 
	 * @param window
	 * @throws IOException
	 *///TODO DA CANCELLARE quello scritto sopra
	boolean addScene(AgroludosWindow window, AgroludosTO mainTO) throws IOException{
		boolean res = false;
		if(this.addSceneSupp(window) || window.isDialog())
			res = true;
		window.getController().initializeView(mainTO);
		return res;
	}

	Scene getScene(String viewName){
		return scenes.get(viewName);
	}
}