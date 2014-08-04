package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;

public class ControllerPartecipanteMain implements Initializable{

	//pane centrali
	@FXML private GridPane paneCompetizioni, paneIscrizioni;
	
	//button mainView
	@FXML private Button btnGestManComp,btnGestIscrizioni;
	

	//texfield 



	private FC frontController = FC.getInstance();
	private FrameRequest richiesta;


	public void initialize(URL arg0, ResourceBundle arg1) {
		//setto visibile solo il primo pane
		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);
	}

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);
	}

	@FXML protected void btnGestIscrizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(false);
		this.paneIscrizioni.setVisible(true);
	}

	

}
