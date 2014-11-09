package agroludos.presentation.views.amministratore;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiSistemaTO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerConfSistema extends AgroludosController {
	private String viewName;

	@FXML private Label lblNomeError;
	@FXML private Label lblCognomeError;
	@FXML private Label lblUsernameError;
	@FXML private Label lblPasswordError;
	@FXML private Label lblEmailError;
	@FXML private Label lblTelefonoError;
	@FXML private TextField txtNomeMds;
	@FXML private TextField txtCognomeMds;
	@FXML private TextField txtUsernameMds;	
	@FXML private TextField txtEmailMds;
	@FXML private TextField txtTelefonoMds;
	@FXML private PasswordField txtPasswordMds;

	private ManagerDiSistemaTO mdsto;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
		this.mdsto = toFact.createMdSTO();
		this.hideErrors();
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {

	}

	@FXML protected void btnConfermaConfClicked(MouseEvent event){
		this.hideErrors();
		this.mdsto.setNome(this.txtNomeMds.getText());
		this.mdsto.setCognome(this.txtCognomeMds.getText());
		this.mdsto.setUsername(this.txtUsernameMds.getText());
		this.mdsto.setPassword(this.txtPasswordMds.getText());
		this.mdsto.setEmail(this.txtEmailMds.getText());
		this.mdsto.setTelefono(this.txtTelefonoMds.getText());
		this.richiesta = this.getRichiesta(this.mdsto, "nuovoManagerDiSistema", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
	}

	private void hideErrors(){
		this.lblNomeError.setVisible(false);
		this.lblCognomeError.setVisible(false);
		this.lblUsernameError.setVisible(false);
		this.lblPasswordError.setVisible(false);
		this.lblEmailError.setVisible(false);
		this.lblTelefonoError.setVisible(false);
	}

	private void showErrors(ErrorTO errors, Label lblError, String errorKey){
		if(errors.hasError(this.getError(errorKey))){
			String nomeKey = this.getError(errorKey);
			lblError.setVisible(true);
			lblError.setText(errors.getError(nomeKey));
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals(this.getCommandName("nuovoManagerDiSistema") )){
			Object res = response.getRespData();
			if(res instanceof Boolean){
				boolean nuovoMds = (Boolean)res;
				if(nuovoMds)
					this.setVista("login");
			} else if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;

				if(errors.hasError(this.getError("nomeKey"))){
					this.showErrors(errors, this.lblNomeError, "nomeKey");
				} 

				if(errors.hasError(this.getError("cognKey"))){
					this.showErrors(errors, this.lblCognomeError, "cognKey");
				}

				if(errors.hasError(this.getError("usernameKey"))){
					this.showErrors(errors, this.lblUsernameError, "usernameKey");
				}

				if(errors.hasError(this.getError("passwordKey"))){
					this.showErrors(errors, this.lblPasswordError, "passwordKey");
				}

				if(errors.hasError(this.getError("emailKey"))){
					this.showErrors(errors, this.lblEmailError, "emailKey");
				}

				if(errors.hasError(this.getError("telefonoKey"))){
					this.showErrors(errors, this.lblTelefonoError, "telefonoKey");
				}

			} else if(res instanceof String){
				String msg = (String)res;
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}
	}
}