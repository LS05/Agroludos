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
	private Stage mainStage;

	ViewsCache(){
		this.views = new HashMap<String, AgroludosWindow>();
	}

	boolean checkView(String viewName) {
		return this.views.containsKey(viewName);
	}

	AgroludosWindow getView(String viewName) {
		return this.views.get(viewName);
	}

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
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setScene(dialogScene);
				stage.setTitle(window.getTitle());
				stage.setResizable(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				window.setStage(stage);
				PositionHandler.centerComp(window.getStage(), window.getStage().getScene());

			}else if(window.isMainView()){
				height = bounds.getHeight();
				width =  bounds.getWidth();		
				//creo la scena	e la aggiungo al mainStage
				Scene mainViewScene = new Scene(root, width, height);
				Stage stage = new Stage(StageStyle.DECORATED);
				stage.setScene(mainViewScene);
				stage.setTitle(window.getTitle());
				//setto il mainStage
				window.setStage(stage);
			}
		}
		this.views.put(window.getName(), window);

	}

	public void addMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void closeMainStage() {
		this.mainStage.close();
	}
}