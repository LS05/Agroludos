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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.datepicker.AgroDatePicker;
import agroludos.to.AgroludosTO;
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
	@FXML private TextField txtSesso;
	@FXML private TextField txtNTSanitaria;
	@FXML private TextField txtRevealPassword;
	@FXML private PasswordField txtPassword;
	@FXML private Button btnRegistrati;
	@FXML private Button btnCarica;
	@FXML private GridPane paneDataNasc;
	@FXML private GridPane paneDataCert;
	@FXML private CheckBox checkBoxReveal;

	private FileChooser fileChooser;

	private AgroDatePicker dataCertPicker;
	private AgroDatePicker dataNascPicker;

	private List<TipoUtenteTO> listTipiU;

	private List<StatoUtenteTO> listStatiU;

	private File fileSrc;

	private PartecipanteTO parTO;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;

		this.parTO = toFact.createPartecipanteTO();

		this.fileChooser = new FileChooser();

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoUtente", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.btnRegistrati.setDisable(false);
		this.btnCarica.setDisable(false);

		this.dataNascPicker = new AgroDatePicker();
		this.dataCertPicker = new AgroDatePicker();
		this.paneDataCert.getChildren().add(this.dataCertPicker.getDatePicker());
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

		//TODO Da Eliminare
		this.txtCF.setText("zgrfnc88e27a285p");
		this.txtCognome.setText("Francesco");
		this.txtNome.setText("francesco");
		this.txtEmail.setText("francesco@fra.it");
		this.txtIndirizzo.setText("via firenze 33");
		this.txtNTSanitaria.setText("321654987987654");
		this.txtPassword.setText("123456");
		this.txtSesso.setText("m");
		this.txtUsername.setText("");
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		// TODO modificare il sesso come intero (due checkbox e 1 per m e 0 per f) ?
		if(mainTO instanceof PartecipanteTO){
			this.parTO = (PartecipanteTO) mainTO;
			this.txtNome.setText(this.parTO.getNome());
			this.txtCognome.setText(this.parTO.getCognome());
			this.txtUsername.setText(this.parTO.getUsername());
			this.txtPassword.setText(this.parTO.getPassword());
			this.txtCF.setText(this.parTO.getCf());
			this.txtIndirizzo.setText(this.parTO.getIndirizzo());
			this.txtSesso.setText(this.parTO.getSesso());
			this.txtEmail.setText(this.parTO.getEmail());
			this.dataNascPicker.setSelectedDate(this.parTO.getDataNasc());
			this.txtNTSanitaria.setText(this.parTO.getNumTS());
			this.dataCertPicker.setSelectedDate(this.parTO.getDataSRC());
		}
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		this.parTO.setNome(this.txtNome.getText());
		this.parTO.setCognome(this.txtCognome.getText());
		this.parTO.setUsername(this.txtUsername.getText());
		this.parTO.setPassword(this.txtPassword.getText());
		this.parTO.setCf(this.txtCF.getText());
		this.parTO.setIndirizzo(this.txtIndirizzo.getText());
		this.parTO.setSesso(this.txtSesso.getText());
		this.parTO.setEmail(this.txtEmail.getText());
		this.parTO.setDataNasc(this.dataNascPicker.getSelectedDate());
		this.parTO.setNumTS(this.txtNTSanitaria.getText());
		this.parTO.setDataSRC(this.dataNascPicker.getSelectedDate());
		if(this.fileSrc == null){
			this.parTO.setSrc("");
		} else {
			this.parTO.setSrc(this.fileSrc.getAbsolutePath());
		}

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.parTO,"inserisciPartecipante", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.close();
	}

	@FXML protected void btnCarica(MouseEvent event) {
		File file = fileChooser.showOpenDialog(nav.getStage(this.viewName));
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

		if(commandName.equals( this.reqProperties.getProperty("getAllTipoUtente") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listTipiU = (List<TipoUtenteTO>)res;
				this.parTO.setTipoUtente(this.listTipiU.get(2));
			}
		}else if(commandName.equals( this.reqProperties.getProperty("getAllStatoUtente") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listStatiU = (List<StatoUtenteTO>)res;
				this.parTO.setStatoUtente(this.listStatiU.get(1));
			}
		}else if(commandName.equals( this.reqProperties.getProperty("inserisciPartecipante") )){
			Object res = response.getRespData();
			if(res instanceof PartecipanteTO){
				this.parTO = toFact.createPartecipanteTO();
			}
		}

	}

}