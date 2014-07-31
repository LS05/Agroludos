package agroludos.presentation.views.login;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;
import agroludos.utility.SecurePassword;

public class ControllerLogin {

	@FXML private Button btnLogin;
	@FXML private Button btnPswDimenticata;
	@FXML private Button btnRegistrati;
	
	//texfield 
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;

	//hashmap dei contenuti delle text
	private Map<String, String> paramLogin = new HashMap<>();


	private FC frontController = FC.getInstance();

	private FrameRequest richiesta;


	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML protected void btnLogin(MouseEvent event) {
		//controllo la validità delle textfield	

		if((this.txtUsername.getText().length() != 0)  &&
				(this.txtPassword.getText().length() != 0)  
				) {

			//sicurezza password
			String securePassword = null;
			try {
				securePassword = SecurePassword.stringToMD5(this.txtPassword.getText());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//copio il contenuto delle textfield nell'hashmap parametri
			paramLogin.put("username", txtUsername.getText());
			paramLogin.put("password", securePassword);

			this.richiesta = new FrameRequest(paramLogin,"cofermaLogin");
			boolean res = (boolean) this.frontController.eseguiRichiesta(richiesta);

			//se il login è andato a buon fine
			if(res){
				//entra nel pannello dell'untente che ha effettuato il login
			}
			else
				System.out.println("Utente non registrato");
		}
		else {
			System.out.println("Campi vuoti");
		}

	}

	@FXML protected void btnPswDimenticata(MouseEvent event) {
		// TO DO
		System.out.println("Password Dimenticata");
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		//TO DO
		System.out.println("Registrazione");
	}

	


	
}
