package agroludos.presentation.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

			Scene view = new Scene(root, window.getWidth(), window.getHeight());
			this.scenes.put(window.getName(), view);
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	void addScene(AgroludosWindow window) throws IOException{
		if(this.addSceneSupp(window) || window.isDialog()){
			window.getController().initializeView();
		}
	}

	void addScene(AgroludosWindow window, AgroludosTO mainTO) throws IOException{
		if(this.addSceneSupp(window) || window.isDialog()){
			window.getController().initializeView(mainTO);
		}
	}

	Scene getScene(String viewName){
		return scenes.get(viewName);
	}
}