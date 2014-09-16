package agroludos.presentation.views.mds;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.ManagerDiCompetizioneTO;

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
	
	private ManagerDiCompetizioneTO mdcTO;
	private AgroResponse risposta;
	
	@Override
	public void initializeView() {
		// TODO Auto-generated method stub	
	}

	public void visualizzaManagerDiCompetizione() {
		this.txtUsername.setText(this.mdcTO.getUsername());
		this.txtNome.setText(this.mdcTO.getNome());
		this.txtCognome.setText(this.mdcTO.getCognome());
		this.txtEmail.setText(this.mdcTO.getEmail());
	}
	
	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		ManagerDiCompetizioneTO mdcTO = toFact.createMdCTO();
		mdcTO.setCognome(this.txtCognome.getText());
		mdcTO.setNome(this.txtNome.getText());
		mdcTO.setUsername(this.txtUsername.getText());
		mdcTO.setEmail(this.txtEmail.getText());
		mdcTO.setStato(this.cmbStato.getSelectionModel().getSelectedIndex());
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(mdcTO, "confermaModificaMDC");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}
	
	@FXML public void annullaModificaManagerDiCompetizion(MouseEvent event){
	    Stage stage = (Stage) annullaModifica.getScene().getWindow();
	    stage.close();
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub
	}
}