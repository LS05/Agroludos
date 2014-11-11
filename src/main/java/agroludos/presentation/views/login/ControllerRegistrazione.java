package agroludos.presentation.views.login;

import java.io.File;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.datepicker.AgroDatePicker;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.TipoUtenteTO;

public class ControllerRegistrazione extends AgroludosController{

	private String viewName;

	@FXML private Label lblSrc;
	@FXML private TextField txtUsername;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private TextField txtEmail;
	@FXML private TextField txtIndirizzo;
	@FXML private TextField txtCF;
	@FXML private TextField txtNTSanitaria;
	@FXML private TextField txtRevealPassword;
	@FXML private PasswordField txtPassword;
	@FXML private Button btnRegistrati;
	@FXML private Button btnCarica;
	@FXML private GridPane paneDataNasc;
	@FXML private GridPane paneDataCert;
	@FXML private CheckBox checkBoxReveal;
	@FXML private Label lblNomeError;
	@FXML private Label lblCognomeError;
	@FXML private Label lblDataSrcError;
	@FXML private Label lblTesSanError;
	@FXML private Label lblUsernameError;
	@FXML private Label lblPasswordError;
	@FXML private Label lblIndirizzoError;
	@FXML private Label lblCfError;
	@FXML private Label lblSrcError;
	@FXML private Label lblEmailError;
	@FXML private Label lblDataNascError;
	@FXML private Label lblSessoError;
	@FXML private RadioButton radioM;
	@FXML private RadioButton radioF;

	private FileChooser fileChooser;
	private AgroDatePicker dataSrcPicker;
	private AgroDatePicker dataNascPicker;

	private List<TipoUtenteTO> listTipiU;

	private List<StatoUtenteTO> listStatiU;

	private File fileSrc;

	private PartecipanteTO parTO;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	private boolean errFlag;

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;

		this.errFlag = false;

		this.hideErrors();

		this.parTO = toFact.createPartecipanteTO();

		this.fileChooser = new FileChooser();

		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta("getAllTipoUtente", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.btnRegistrati.setDisable(false);
		this.btnCarica.setDisable(false);

		this.dataNascPicker = new AgroDatePicker();
		this.dataSrcPicker = new AgroDatePicker();
		this.paneDataCert.getChildren().add(this.dataSrcPicker.getDatePicker());
		this.paneDataNasc.getChildren().add(this.dataNascPicker.getDatePicker());

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

		this.radioM.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
				if(newVal){
					radioF.setSelected(false);
				}
			}
		});

		this.radioF.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
				if(newVal){
					radioM.setSelected(false);
				}
			}
		});

		//TODO Da Eliminare
		this.txtCF.setText("zgrfnc88e27a285p");
		this.txtCognome.setText("Francesco");
		this.txtNome.setText("francesco");
		this.txtEmail.setText("francesco@fra.it");
		this.txtIndirizzo.setText("via firenze 33");
		this.txtNTSanitaria.setText("3216549879876542");
		this.txtPassword.setText("123456");
		this.txtUsername.setText("");
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {

	}

	private void hideErrors(){
		this.lblNomeError.setVisible(false);
		this.lblCognomeError.setVisible(false);
		this.lblDataSrcError.setVisible(false);
		this.lblTesSanError.setVisible(false);
		this.lblUsernameError.setVisible(false);
		this.lblPasswordError.setVisible(false);
		this.lblIndirizzoError.setVisible(false);
		this.lblCfError.setVisible(false);
		this.lblSrcError.setVisible(false);
		this.lblEmailError.setVisible(false);
		this.lblDataNascError.setVisible(false);
		this.lblSessoError.setVisible(false);
	}

	private void showErrors(ErrorTO errors, Label lblError, String errorKey){
		if(errors.hasError(this.getError(errorKey))){
			String nomeKey = this.getError(errorKey);
			lblError.setVisible(true);
			lblError.setText(errors.getError(nomeKey));
		} 
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		this.hideErrors();
		this.parTO.setNome(this.txtNome.getText());
		this.parTO.setCognome(this.txtCognome.getText());
		this.parTO.setUsername(this.txtUsername.getText());
		this.parTO.setPassword(this.txtPassword.getText());
		this.parTO.setCf(this.txtCF.getText());
		this.parTO.setIndirizzo(this.txtIndirizzo.getText());

		if(this.radioM.isSelected()){
			this.parTO.setSesso(this.radioM.getText());
		} else if(this.radioF.isSelected()){
			this.parTO.setSesso(this.radioF.getText());
		} else{
			this.parTO.setSesso("");
		}

		this.parTO.setEmail(this.txtEmail.getText());
		this.parTO.setDataNasc(this.dataNascPicker.getSelectedDate());
		this.parTO.setNumTS(this.txtNTSanitaria.getText());
		this.parTO.setDataSRC(this.dataSrcPicker.getSelectedDate());

		if(this.fileSrc == null){
			this.parTO.setSrc("");
		} else {
			this.parTO.setSrc(this.fileSrc.getAbsolutePath());
		}

		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.parTO,"inserisciPartecipante", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		if( !this.errFlag )
			this.close();
	}

	@FXML protected void btnCarica(MouseEvent event) {
		File file = this.fileChooser.showOpenDialog(this.getStage(this.viewName));
		if (file != null) {
			this.fileSrc = file;
			this.lblSrc.setText(this.fileSrc.getName());
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals(this.getCommandName("getAllTipoUtente") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listTipiU = (List<TipoUtenteTO>)res;
				this.parTO.setTipoUtente(this.listTipiU.get(2));
			}
		} else if(commandName.equals(this.getCommandName("getAllStatoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				this.listStatiU = (List<StatoUtenteTO>)res;
				this.parTO.setStatoUtente(this.listStatiU.get(1));
			}

		} else if(commandName.equals(this.getCommandName("inserisciPartecipante") )){
			Object res = response.getRespData();

			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;
				this.errFlag = true;

				if(errors.hasError(this.getError("nomeKey"))){
					this.showErrors(errors, this.lblNomeError, "nomeKey");
				} 

				if(errors.hasError(this.getError("cognKey"))){
					this.showErrors(errors, this.lblCognomeError, "cognKey");
				}

				if(errors.hasError(this.getError("cfKey"))){
					this.showErrors(errors, this.lblCfError, "cfKey");
				}

				if(errors.hasError(this.getError("dataSrcKey"))){
					this.showErrors(errors, this.lblDataSrcError, "dataSrcKey");
				}

				if(errors.hasError(this.getError("srcKey"))){
					this.showErrors(errors, this.lblSrcError, "srcKey");
				}

				if(errors.hasError(this.getError("tesKey"))){
					this.showErrors(errors, this.lblTesSanError, "tesKey");
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

				if(errors.hasError(this.getError("indrizzzoKey"))){
					this.showErrors(errors, this.lblIndirizzoError, "indrizzzoKey");
				}

				if(errors.hasError(this.getError("dataNascKey"))){
					this.showErrors(errors, this.lblDataNascError, "dataNascKey");
				}

				if(errors.hasError(this.getError("sessoKey"))){
					this.showErrors(errors, this.lblSessoError, "sessoKey");
				}
			} else if( res instanceof String ){
				this.close();
				this.errFlag = true;
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}

	}
}