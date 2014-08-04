package agroludos.presentation.views.mdc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;

public class ControllerMdcMain implements Initializable{

	//pane
	@FXML private GridPane paneCompetizioni;
	//button mainView
	@FXML private Button btnPaneComptizioni;
	//texfield 



	private FC frontController = FC.getInstance();
	private FrameRequest richiesta;


	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}



}
