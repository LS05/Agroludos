package agroludos.presentation.views.utenti;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.SuccessMessageTO;
import agroludos.to.UtenteTO;

public class ControllerModificaDatiAccesso extends AgroludosController implements Initializable{

	private String viewName;

	@FXML private TextField txtEmail;
	@FXML private TextField txtRevealPassword;
	@FXML private PasswordField txtPassword;
	@FXML private Button btnRegistrati;
	@FXML private CheckBox checkBoxReveal;
	@FXML private Label lblPasswordError;
	@FXML private Label lblEmailError;

	private AgroResponse risposta;
	private AgroRequest richiesta;
	
	private UtenteTO uTO;

	private ResourceBundle resources;


	@Override
	public void initialize(URL arg0, ResourceBundle res) {
		this.resources = res;
		
	}
	
	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;

		this.hideErrors();

		this.checkBoxReveal.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
				if(newVal){
					txtPassword.setVisible(false);
					txtRevealPassword.setVisible(true);
					txtRevealPassword.setText(txtPassword.getText());
				} else {
					txtPassword.setVisible(true);
					txtPassword.setText(txtRevealPassword.getText());
					txtRevealPassword.setVisible(false);
				}
			}
		});

	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {

	}

	private void hideErrors(){
		this.lblPasswordError.setVisible(false);
		this.lblEmailError.setVisible(false);
	}

	private void showErrors(ErrorTO errors, Label lblError, String errorKey){
		if(errors.hasError(this.getError(errorKey))){
			String nomeKey = this.getError(errorKey);
			lblError.setVisible(true);
			lblError.setText(errors.getError(nomeKey));
		} 
	}

	@FXML protected void modificaDatiAccesso(MouseEvent event) {
		this.hideErrors();
		this.uTO.setPassword(this.txtPassword.getText());
		this.uTO.setEmail(this.txtEmail.getText());
		
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.uTO,"modificaDatiAccesso", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals(this.getCommandName("modificaDatiAccesso") )){
			Object res = response.getRespData();

			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;

				if(errors.hasError(this.getError("passwordKey"))){
					this.showErrors(errors, this.lblPasswordError, "passwordKey");
				}

				if(errors.hasError(this.getError("emailKey"))){
					this.showErrors(errors, this.lblEmailError, "emailKey");
				}

			} else if( res instanceof String ){
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
				
			}else if( res instanceof UtenteTO ){
				this.close();
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.resources.getString("key99"));
				this.setVista("messageDialog", succMessage);
			}
		}

	}

}

