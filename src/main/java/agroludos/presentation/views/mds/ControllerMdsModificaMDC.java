package agroludos.presentation.views.mds;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ManagerDiCompetizioneTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerMdsModificaMDC extends AgroludosController{

	@FXML private Button annullaModifica;
	@FXML private Button confermaModificaMDC;
	@FXML private TextField txtUsername;
	@FXML private TextField txtEmail;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private ComboBox<String> cmbStato;
	@FXML private Label lblMessaggioModifica;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	private ManagerDiCompetizioneTO mdcTO;

	@Override
	public void initializeView() {
		this.lblMessaggioModifica.setVisible(false);
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.mdcTO  = (ManagerDiCompetizioneTO)mainTO;

		this.txtUsername.setText(this.mdcTO.getUsername());
		this.txtCognome.setText(this.mdcTO.getCognome());
		this.txtNome.setText(this.mdcTO.getNome());
		this.txtEmail.setText(this.mdcTO.getEmail());
		
		//TODO Query sugli stati
		ObservableList<String> listStati = FXCollections.observableArrayList();
		listStati.add("disattivo");
		listStati.add("attivo");

		this.cmbStato.setItems(listStati);
		this.cmbStato.setValue(this.mdcTO.getStatoUtente().getNome());
	}

	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		this.mdcTO.setNome(this.txtNome.getText());
		this.mdcTO.setCognome(this.txtCognome.getText());
		this.mdcTO.setUsername(this.txtUsername.getText());
		this.mdcTO.setEmail(this.txtEmail.getText());
		//TODO rivedere come far cambiare ;
		this.mdcTO.getStatoUtente().setId(this.cmbStato.getSelectionModel().getSelectedIndex());

		this.richiesta = this.getRichiesta(mdcTO, "modificaManagerDiCompetizione");
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		if(this.risposta.getRespData() instanceof ManagerDiCompetizioneTO){
			this.lblMessaggioModifica.setVisible(true);
		}

	}

	@FXML public void annullaModificaManagerDiCompetizion(MouseEvent event){
		Stage stage = (Stage) this.annullaModifica.getScene().getWindow();
		stage.hide();
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}