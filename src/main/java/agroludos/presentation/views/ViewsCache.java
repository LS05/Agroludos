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

class ViewsCache {

	//hash map per le view
	private Map<String, AgroludosWindow> views;

	ViewsCache(){
		this.views = new HashMap<String, AgroludosWindow>();
	}


	void setStage(String key, Stage stage){
		this.views.get(key).setStage(stage);
	}

	boolean checkView(String viewName) {
		return this.views.containsKey(viewName);
	}

	AgroludosWindow getView(String viewName) {
		return this.views.get(viewName);
	}

	public void addView(AgroludosWindow window, Stage mainStage) throws IOException {
		//carico l'fxml e setto scena e stage
		FXMLLoader loader = window.getLoader();
		Pane root = null;
		try {
			root = (Pane)loader.load();
		} catch (IOException e) {
			throw new IOException(e.getMessage(), e.getCause());
		}
		//posiziono la view e setto la grandezza
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

		//creo la scena		
		Scene scene = new Scene(root, width, height);
		//creo lo stage se è un dialog
		if(window.isDialog()){
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.setScene(scene);
			stage.setTitle(window.getTitle());
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(mainStage);
			window.setOwnerStage(mainStage);
			window.setStage(stage);
			PositionHandler.centerComp(window.getStage(), window.getStage().getScene());
		}else{
			window.setStage(mainStage);
			mainStage.setScene(scene);
		}
		this.views.put(window.getName(), window);

	}
}