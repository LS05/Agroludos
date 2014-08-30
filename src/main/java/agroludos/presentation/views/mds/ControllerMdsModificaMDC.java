package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.ManagerDiCompetizioneTO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerMdsModificaMDC extends AgroludosController implements Initializable{
	@FXML Button annullaModifica;
	@FXML Button confermaModificaMDC;
	@FXML TextField txtUsername;
	@FXML TextField txtEmail;
	@FXML TextField txtNome;
	@FXML TextField txtCognome;
	@FXML ComboBox<String> cmbStato;
	
	private Map<String, String> dataMDC = new HashMap<String, String>();
	
	private AgroRequest richiesta;
	
	private ManagerDiCompetizioneTO mdcTO;
	
	@Override
	public void initialize(URL url, ResourceBundle resBoundle) {
		
	}

	public void visualizzaManagerDiCompetizione() {
		this.txtUsername.setText(this.mdcTO.getUsername());
		this.txtNome.setText(this.mdcTO.getNome());
		this.txtCognome.setText(this.mdcTO.getCognome());
		this.txtEmail.setText(this.mdcTO.getEmail());
	}
	
	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		dataMDC.put("id", String.valueOf(this.mdcTO.getId()));
		dataMDC.put("nome", this.txtNome.getText());
		dataMDC.put("cognome", this.txtCognome.getText());
		dataMDC.put("username", this.txtUsername.getText());
		dataMDC.put("email", this.txtEmail.getText());
		dataMDC.put("stato", this.cmbStato.getValue());
		
		this.richiesta = reqFact.createDFrameRequest(dataMDC, "confermaModificaMDC");
		frontController.eseguiRichiesta(richiesta);
		
	}
	
	@FXML public void annullaModificaManagerDiCompetizion(MouseEvent event){
	    Stage stage = (Stage) annullaModifica.getScene().getWindow();
	    stage.close();
	}

}