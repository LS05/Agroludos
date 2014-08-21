package agroludos.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

class ViewsCache {
	private FXMLLoader loader;
	private Scene loadScene;
	
	void setLoader(FXMLLoader loader){
		this.loader = loader;
	}
	
	void setScene(Pane loadView){
		this.loadScene = new Scene(loadView);
	}
	
	public FXMLLoader getLoader() {
		return this.loader;
	}

	public Scene getScene() {
		return this.loadScene;
	}
}