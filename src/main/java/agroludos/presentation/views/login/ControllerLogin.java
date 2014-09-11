package agroludos.presentation.views.login;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.UtenteTO;

public class ControllerLogin extends AgroludosController implements Initializable{

	@FXML private Button btnLogin;
	@FXML private Button btnPswDimenticata;
	@FXML private Button btnRegistrati;

	@FXML private Pane agroLogoPane;

	//texfield 
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	public void initialize(URL url, ResourceBundle resource) {
		this.agroLogoPane.setFocusTraversable(true);
		this.txtUsername.setText("LS05");
		this.txtPassword.setText("891205");
	}

	@FXML protected void txtPassword(javafx.scene.input.KeyEvent evt) {
		if (evt.getCode() == KeyCode.ENTER)
			eseguiLogin();
	}

	@FXML protected void btnLogin(MouseEvent event) {	
		eseguiLogin();
	}

	protected void eseguiLogin() {	
		UtenteTO uto = toFact.createUTO();
		uto.setUsername(this.txtUsername.getText());
		uto.setPassword(this.txtPassword.getText());
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(uto, "autenticazioneUtente");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@FXML protected void btnPswDimenticata(MouseEvent event) {
		//TODO
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		//TODO
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("autenticazioneUtente")){
			UtenteTO res = (UtenteTO)response.getRespData();
			if(res != null){
				//entra nel pannello dell'untente che ha effettuato il login
				nav.setVista(res.getRuolo());
			}
			else
				System.out.println("Utente non registrato");
		}
	}

}