package agroludos.main;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;

public class ControllerError  extends AgroludosController{
	@FXML private Label lblErrore;
	@FXML private Button btnOk;
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("checkMds")){
			String res = (String)response.getRespData();
			this.lblErrore.setText(res);
		}
	}
	@FXML protected void btnOkClicked(MouseEvent event) {	
		Node  source = (Node)  event.getSource(); 
	    Stage stage  = (Stage) source.getScene().getWindow();
	    stage.close();
		nav.setVista("configurazione");
	}
	
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
		
	}
}
