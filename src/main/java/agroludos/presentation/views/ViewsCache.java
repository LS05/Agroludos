package agroludos.presentation.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import agroludos.exceptions.ViewLoadingException;
import agroludos.presentation.views.xml.AgroludosWindow;

class ViewsCache {
	
	private Map<String, Scene> scenes;

	ViewsCache(){
		this.scenes = new HashMap<String, Scene>();
	}
	
	void addScene(AgroludosWindow window){
		if(!this.scenes.containsKey(window.getName())){
			FXMLLoader loader = window.getLoader();
			
			Pane root = null;
			
			try {
				root = (Pane)loader.load();
			} catch (IOException e) {
				throw new ViewLoadingException(e.getMessage(), e.getCause());
			}
			
			Scene view = new Scene(root);
			this.scenes.put(window.getName(), view);
		}
	}
	
	Scene getScene(String viewName){
		return scenes.get(viewName);
	}
}
