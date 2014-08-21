package agroludos.presentation.views.utility;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe che si occupa di centrare lo stage in base alle dimensioni
 * della scena.
 * 
 * @author Luca Suriano
 *
 */
public class PositionHandler {

	public static void centerComp(Stage stage, Scene scene){
		Pane p = (Pane)scene.getRoot();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - p.getPrefWidth()) / 2); 
		stage.setY((primScreenBounds.getHeight() - p.getPrefHeight()) / 2);
	}

}
