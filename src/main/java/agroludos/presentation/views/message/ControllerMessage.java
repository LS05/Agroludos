package agroludos.presentation.views.message;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;	
import agroludos.to.MessageTO;
import agroludos.to.SuccessMessageTO;

public class ControllerMessage extends AgroludosController{

	private String viewName;

	@FXML private Label lblMessage;
	@FXML private Label lblSuccTitle;
	@FXML private Label lblErrTitle;
	@FXML private Pane successIconPane;
	@FXML private Pane errorIconPane;

	private MessageTO message;

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.successIconPane.setVisible(false);
		this.errorIconPane.setVisible(false);
		this.lblSuccTitle.setVisible(false);
		this.lblErrTitle.setVisible(false);
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof MessageTO){
			this.message = (MessageTO) mainTO;
			this.lblMessage.setText(this.message.getMessage());
			if(mainTO instanceof SuccessMessageTO){
				this.successIconPane.setVisible(true);
				this.lblSuccTitle.setVisible(true);
			} else if(mainTO instanceof ErrorMessageTO){
				this.errorIconPane.setVisible(true);
				this.lblErrTitle.setVisible(true);
			}
		}
	}

	@FXML protected void btnOkClicked(MouseEvent event) {	
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