package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoUtenteTO;
import agroludos.to.SuccessMessageTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
	@FXML private Label lblStatoError;
	@FXML private ComboBox<String> cmbStato;

	private List<StatoUtenteTO> listStatiUtente;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	private ManagerDiCompetizioneTO mdcTO;

	private ResourceBundle resources;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.resources = resources;	
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
		this.flagError = false;
		this.lblUsernameError.setVisible(false);
		this.lblNomeError.setVisible(false);
		this.lblCognomeError.setVisible(false);
		this.lblEmailError.setVisible(false);
		this.lblStatoError.setVisible(false);
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

			this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

			ObservableList<String> listStati = FXCollections.observableArrayList();
			for(StatoUtenteTO stato : this.listStatiUtente){
				listStati.add(stato.getNome());
			}
			this.cmbStato.setItems(listStati);
			this.cmbStato.setValue(this.mdcTO.getStatoUtente().getNome());
		}
	}

	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		this.mdcTO.setNome(this.txtNome.getText());
		this.mdcTO.setCognome(this.txtCognome.getText());
		this.mdcTO.setUsername(this.txtUsername.getText());
		this.mdcTO.setEmail(this.txtEmail.getText());

		Integer statoSel = this.cmbStato.getSelectionModel().getSelectedIndex();
		StatoUtenteTO stato = this.listStatiUtente.get(statoSel);
		this.mdcTO.setStatoUtente(stato);

		this.richiesta = this.getRichiesta(this.mdcTO, "modificaManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		if(!this.flagError){
			SuccessMessageTO succTO = toFact.createSuccMessageTO();
			succTO.setMessage(this.resources.getString("key99"));
			this.setVista("messageDialog", succTO);
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

		if( commandName.equals( this.getCommandName("getAllStatoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoUtenteTO> listStati = (List<StatoUtenteTO>)res;
				this.listStatiUtente = listStati;
			}

		} else if( commandName.equals( this.getCommandName("modificaManagerDiCompetizione") )){
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

				if(errors.hasError(this.getError("emailKey"))){
					String emailKey = this.getError("emailKey");
					this.lblEmailError.setVisible(true);
					this.lblEmailError.setText(errors.getError(emailKey));
				}

			}
		}

	}
}