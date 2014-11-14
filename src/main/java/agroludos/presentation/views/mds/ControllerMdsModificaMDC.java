package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.SuccessMessageTO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerMdsModificaMDC extends AgroludosController implements Initializable{
	private String viewName;
	private boolean flagError;

	@FXML private TextField txtUsername;
	@FXML private TextField txtEmail;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private Label lblUsernameError;
	@FXML private Label lblNomeError;
	@FXML private Label lblCognomeError;
	@FXML private Label lblEmailError;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ManagerDiCompetizioneTO mdcTO;

	private ResourceBundle resources;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;	
	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.flagError = false;
		this.lblUsernameError.setVisible(false);
		this.lblNomeError.setVisible(false);
		this.lblCognomeError.setVisible(false);
		this.lblEmailError.setVisible(false);
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof ManagerDiCompetizioneTO){
			this.flagError = false;
			this.mdcTO = (ManagerDiCompetizioneTO)mainTO;
			this.txtUsername.setText(this.mdcTO.getUsername());
			this.txtCognome.setText(this.mdcTO.getCognome());
			this.txtNome.setText(this.mdcTO.getNome());
			this.txtEmail.setText(this.mdcTO.getEmail());

		}
	}

	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		this.lblCognomeError.setVisible(false);
		this.lblEmailError.setVisible(false);
		this.lblNomeError.setVisible(false);
		this.lblUsernameError.setVisible(false);
		this.flagError=false;

		this.mdcTO.setNome(this.txtNome.getText());
		this.mdcTO.setCognome(this.txtCognome.getText());
		this.mdcTO.setUsername(this.txtUsername.getText());
		this.mdcTO.setEmail(this.txtEmail.getText());

		this.richiesta = this.getRichiesta(this.mdcTO, "modificaManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		if(!this.flagError){
			this.close();
			SuccessMessageTO succTO = toFact.createSuccMessageTO();
			succTO.setMessage(this.resources.getString("key99"));
			this.setVista("messageDialog", succTO);
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("modificaManagerDiCompetizione") )){
			Object res = response.getRespData();
			this.flagError = true;
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;


				if(errors.hasError(this.getError("nomeKey"))){
					String nomeKey = this.getError("nomeKey");
					this.lblNomeError.setVisible(true);
					this.lblNomeError.setText(errors.getError(nomeKey));
				} 

				if(errors.hasError(this.getError("cognKey"))){
					String cognomeKey = this.getError("cognKey");
					this.lblCognomeError.setVisible(true);
					this.lblCognomeError.setText(errors.getError(cognomeKey));
				}

				if(errors.hasError(this.getError("usernameKey"))){
					String usernameKey = this.getError("usernameKey");
					this.lblUsernameError.setVisible(true);
					this.lblUsernameError.setText(errors.getError(usernameKey));
				}

				if(errors.hasError(this.getError("emailKey"))){
					String emailKey = this.getError("emailKey");
					this.lblEmailError.setVisible(true);
					this.lblEmailError.setText(errors.getError(emailKey));
				}
			}if(res instanceof String){
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}

	}
}