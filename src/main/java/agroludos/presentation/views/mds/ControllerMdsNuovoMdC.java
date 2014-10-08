package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;

public class ControllerMdsNuovoMdC extends AgroludosController implements Initializable{
	private String viewName;
	private ResourceBundle boundle;
	
	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.boundle = res;
	}
	
	@Override
	protected void initializeView(AgroludosTO mainTO) {
		
		// TODO Auto-generated method stub
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}
	
	@FXML protected void confermaNuovoManagerDiCompetizione(MouseEvent event){
		
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub
		
	}
}