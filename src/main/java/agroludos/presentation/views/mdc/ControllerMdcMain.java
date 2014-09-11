package agroludos.presentation.views.mdc;

import java.net.URL;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcMain extends AgroludosController implements Initializable{

	@FXML private GridPane paneCompetizioni;
	
	@FXML private Button btnPaneComptizioni;

	public void initialize(URL url, ResourceBundle resource) {

	}

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub		
	}
}
