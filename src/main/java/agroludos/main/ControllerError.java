package agroludos.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;

public class ControllerError  extends AgroludosController{
	private String viewName;
	
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
		this.setVista("configurazione");
		this.close();
	}
	
	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub	
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}
}