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

	@FXML private Label lblQuestion;
	@FXML private Button btnYes;  
	@FXML private Button btnNo;

	private QuestionTO questTO;

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
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.questTO.getDataTO(), this.questTO.getRequest(), this.questTO.getViewName());
		this.eseguiRichiesta(this.richiesta, this.risposta);
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

	}

}