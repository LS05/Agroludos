package agroludos.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.QuestionTO;

public class ControllerQuestion extends AgroludosController{

	private String viewName;
	private QuestionTO questTO;
	
	@FXML private Label lblQuestion;
	@FXML private Button btnYes;  
	@FXML private Button btnNo;
	private AgroResponse risposta;
	private AgroRequest richiesta;
	

	
	
	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.questTO = (QuestionTO) mainTO;
		
		this.lblQuestion.setText(this.questTO.getQuestion());
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		
	}

	
	@FXML private void btnYes(){
		this.close();
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.questTO.getDataTO(), this.questTO.getRequest(), this.questTO.getViewName());
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		nav.closeVista(this.questTO.getViewName());
		
	}  
	
	@FXML private void btnNo(){
		this.close();
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
