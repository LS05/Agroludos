package agroludos.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.SuccTO;

public class ControllerSuccess  extends AgroludosController{
	private String viewName;
	
	@FXML private Label lblSuccess;
	@FXML private Button btnOk;
	SuccTO mainTO;

	@FXML protected void btnOkClicked(MouseEvent event) {	
		this.close();
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
		
	}
	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.mainTO = (SuccTO) mainTO;
		this.lblSuccess.setText(this.mainTO.getMessage());

	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected String getViewName() {
		return this.viewName;
	}
}