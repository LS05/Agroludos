package agroludos.presentation.views.mdc;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcMain extends AgroludosController{

	@FXML private GridPane paneCompetizioni;
	
	@FXML private Button btnPaneComptizioni;
	@FXML private Button btnNuovaCompetizione;

	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
		
	}

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub		
	}
}
