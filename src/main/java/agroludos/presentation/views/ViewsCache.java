package agroludos.presentation.views;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import agroludos.presentation.views.utility.PositionHandler;
import agroludos.presentation.views.xml.AgroludosWindow;

class ViewsCache {

	private Stack<Stage> currentStage;
	//hash map per le view
	private Map<String, AgroludosWindow> views;

	ViewsCache(){
		this.views = new HashMap<String, AgroludosWindow>();
		this.currentStage = new Stack<Stage>();
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
				//se si tratta di un dialog creo uno stage e inserisco lo stage nello stack
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.setScene(dialogScene);
				stage.setTitle(window.getTitle());
				stage.setResizable(false);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(this.currentStage.peek());
				window.setStage(stage);
				PositionHandler.centerComp(window.getStage(), window.getStage().getScene());
				//inserisco lo stage del dialog nello stack
				this.currentStage.push(stage);
				//aggiungo l'evento in chiusura dello stage
				this.addEventOnStageClose(window);

			}else if(window.isMainView()){
				height = bounds.getHeight();
				width =  bounds.getWidth();		
				//creo la scena	e la aggiungo al mainStage
				Scene mainViewScene = new Scene(root, width, height);
				this.currentStage.get(0).setScene(mainViewScene);
				//setto il mainStage
				//mainStage = this.currentStage.get(0)
				window.setStage(this.currentStage.get(0));
			}
		}
		this.views.put(window.getName(), window);

	}

	void addMainStage(Stage mainStage) {
		this.currentStage.push(mainStage);
	}

	void addEventOnStageClose(AgroludosWindow agw){
		final AgroludosWindow agView = agw;
		//aggiungo l'evento close vista quando si chiude lo stage
		agView.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				//TODO eliminare stampa
				//se si tratta delo stage di un dialog elimino lo stage dallo stack
				if(agView.isDialog() && currentStage.peek().equals(agView.getStage())){
					System.out.println("Dialog is closing");
					currentStage.pop();
				}
			}
		}); 
	}

	protected void pushStack(Stage stage) {
		this.currentStage.push(stage);
	}

	protected void popStack() {
		this.currentStage.pop();
	}

	protected Stage peekStack() {
		return this.currentStage.peek();
	}



}