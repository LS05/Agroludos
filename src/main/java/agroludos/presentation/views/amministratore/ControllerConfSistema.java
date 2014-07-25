package agroludos.presentation.views.amministratore;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerConfSistema implements Initializable{
	@FXML Button btnAvantiConf;
	@FXML Button btnIndietroConf;
	@FXML GridPane databasePane;
	@FXML GridPane managerSistemaPane;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.databasePane.setVisible(true);
        this.managerSistemaPane.setVisible(false);
	}
	
	@FXML protected void btnAvantiClicked(MouseEvent event) {
        this.databasePane.setVisible(false);
        this.managerSistemaPane.setVisible(true);
	}
	@FXML protected void btnIndietroClicked(MouseEvent event) {
        this.databasePane.setVisible(true);
        this.managerSistemaPane.setVisible(false);
	}
}
