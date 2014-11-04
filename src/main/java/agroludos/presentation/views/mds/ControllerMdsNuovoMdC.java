package agroludos.presentation.views.mds;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.numberspinner.NumberSpinner;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoUtenteTO;

public class ControllerMdsNuovoMdC extends AgroludosController implements Initializable{
	private String viewName;
	private boolean flagError;

	private @FXML TextField txtUsername;
	private @FXML PasswordField txtPassword;
	private @FXML TextField txtNome;
	private @FXML TextField txtCognome;
	private @FXML TextField txtRevealPassword;
	private @FXML TextField txtEmail;
	private @FXML Label lblUsernameError;
	private @FXML Label lblPasswordError;
	private @FXML Label lblNomeError;
	private @FXML Label lblCognomeError;
	private @FXML Label lblEmailError;
	private @FXML Label lblStipendioError;
	private @FXML Label lblStatoError;
	private @FXML CheckBox checkBoxReveal;
	private @FXML ComboBox<String> cmbStato;
	private @FXML GridPane mainNuovoMdC;
	private @FXML GridPane paneStipendio;

	private List<TipoUtenteTO> listTipiUtente;
	private List<StatoUtenteTO> listStatiUtente;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ResourceBundle resources;

	private NumberSpinner stipendioMdC;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.flagError = false;
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		this.flagError = false;
		this.lblUsernameError.setVisible(false);
		this.lblPasswordError.setVisible(false);
		this.lblNomeError.setVisible(false);
		this.lblCognomeError.setVisible(false);
		this.lblEmailError.setVisible(false);
		this.lblStipendioError.setVisible(false);
		this.lblStatoError.setVisible(false);

		this.stipendioMdC = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("10"), new DecimalFormat("#,##0.00"));
		this.paneStipendio.getChildren().add(this.stipendioMdC);
		this.richiesta = this.getRichiesta("getAllTipoUtente", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listStati = FXCollections.observableArrayList();
		for(StatoUtenteTO stato : this.listStatiUtente){
			listStati.add(stato.getNome());
		}
		this.cmbStato.setItems(listStati);
		String nomeStato = listStati.get(0);
		this.cmbStato.setValue(nomeStato);

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
	protected String getViewName() {
		return this.viewName;
	}

	@FXML protected void confermaNuovoManagerDiCompetizione(MouseEvent event){
		ManagerDiCompetizioneTO mdcTO = toFact.createMdCTO();
		mdcTO.setNome(this.txtNome.getText());
		mdcTO.setCognome(this.txtCognome.getText());
		mdcTO.setUsername(this.txtUsername.getText());
		mdcTO.setPassword(this.txtPassword.getText());
		mdcTO.setEmail(this.txtEmail.getText());
		mdcTO.setTipoUtente(this.listTipiUtente.get(1));
		mdcTO.setStipendio(this.stipendioMdC.getNumber().doubleValue());
		int selectedStato = this.cmbStato.getSelectionModel().getSelectedIndex();
		mdcTO.setStatoUtente(this.listStatiUtente.get(selectedStato));

		this.richiesta = this.getRichiesta(mdcTO, "nuovoManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		if(!this.flagError){
			SuccessTO succTO = toFact.createSuccessTO();
			succTO.setMessage(this.resources.getString("key132"));
			this.setVista("successDialog", succTO);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("getAllTipoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipoUtenteTO> listTipo = (List<TipoUtenteTO>)res;
				this.listTipiUtente = listTipo;
			}
		} else if( commandName.equals( this.getCommandName("getAllStatoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoUtenteTO> listStati = (List<StatoUtenteTO>)res;
				this.listStatiUtente = listStati;
			}
		} else if( commandName.equals( this.getCommandName("nuovoManagerDiCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;
				this.flagError = true;

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

				if(errors.hasError(this.getError("passwordKey"))){
					String emailKey = this.getError("passwordKey");
					this.lblPasswordError.setVisible(true);
					this.lblPasswordError.setText(errors.getError(emailKey));
				}

				if(errors.hasError(this.getError("emailKey"))){
					String emailKey = this.getError("emailKey");
					this.lblEmailError.setVisible(true);
					this.lblEmailError.setText(errors.getError(emailKey));
				}

			}
		}
	}
}