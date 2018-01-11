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

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof QuestionTO){
			this.questTO = (QuestionTO) mainTO;
			this.lblQuestion.setText(this.questTO.getQuestion());
		}
	}

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;
	}

	@FXML protected void btnYes(){
		this.close();
		AgroRequest richiesta = null;
		AgroResponse risposta = this.getRisposta();

		if(this.questTO.getDataTO() != null){
			richiesta = this.getRichiesta(this.questTO.getDataTO(), this.questTO.getRequest(), this.questTO.getViewName());
		}else{
			richiesta = this.getRichiesta(this.questTO.getRequest(), this.questTO.getViewName());
		}

		this.eseguiRichiesta(richiesta, risposta);
	}  

	@FXML protected void btnNo(){
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