package agroludos.presentation.views.main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MainController {
	@FXML AnchorPane mainPane;
	
	public void setView(Node node) {
		this.mainPane.getChildren().setAll(node);
    }
}
