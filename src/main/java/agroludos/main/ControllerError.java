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
		nav.setVista("configurazione");
		this.close();
	}
	
	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;
		
	}
	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
		
	}

	private String nameView;
	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}
}
