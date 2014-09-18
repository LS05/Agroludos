package agroludos.main;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;

public class ControllerSuccess  extends AgroludosController{
	@FXML private Label lblSuccess;
	@FXML private Button btnOk;
	AgroludosTO mainTO;

	@FXML protected void btnOkClicked(MouseEvent event) {	
		Node  source = (Node)  event.getSource(); 
		Stage stage  = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
	}
	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.mainTO = mainTO;
		if(this.mainTO instanceof CompetizioneTO){
			this.lblSuccess.setText("Competizione inserita con successo");
		}
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub

	}
}


