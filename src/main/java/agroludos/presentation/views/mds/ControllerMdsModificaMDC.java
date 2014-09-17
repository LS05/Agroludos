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
	
	private AgroRequest richiesta;
	
	private AgroResponse risposta;
	
	private ManagerDiCompetizioneTO mdcTO;
	
	
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.mdcTO  = (ManagerDiCompetizioneTO)mainTO;
		
		this.txtUsername.setText(mdcTO.getUsername());
		this.txtCognome.setText(mdcTO.getCognome());
		this.txtNome.setText(mdcTO.getNome());
		this.txtEmail.setText(mdcTO.getEmail());
		
		ObservableList<String> listStati = FXCollections.observableArrayList();
		listStati.add("attivo");
		listStati.add("disattivo");
		
		this.cmbStato.setItems(listStati);
		this.cmbStato.setValue(mdcTO.getNomeStatoUtente());
	}
	
	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		this.mdcTO.setNome(this.txtNome.getText());
		this.mdcTO.setCognome(this.txtCognome.getText());
		this.mdcTO.setUsername(this.txtUsername.getText());
		this.mdcTO.setEmail(this.txtEmail.getText());
		this.mdcTO.setStato(this.cmbStato.getSelectionModel().getSelectedIndex());
		
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(mdcTO, "modificaManagerDiCompetizione");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}
	
	@FXML public void annullaModificaManagerDiCompetizion(MouseEvent event){
	    Stage stage = (Stage) this.annullaModifica.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub
	}
}