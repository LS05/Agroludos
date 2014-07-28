package agroludos.presentation.views.amministratore;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerConfSistema implements Initializable{
	@FXML private Button btnAvantiConf;
	@FXML private Button btnIndietroConf;
	@FXML private Button btnConfermaConfigurazione;
	@FXML private GridPane databasePane;
	@FXML private GridPane managerSistemaPane;
	
	//texfield DataBase
	@FXML private TextField txtTipoDB;
	@FXML private TextField txtPercorsoDB;
	@FXML private TextField txtNomeDB;
	@FXML private TextField txtUsernameDB;
	@FXML private PasswordField txtPasswordDB;
	
	//texfield Manager Di Sistema
	@FXML private TextField txtNomeMds;
	@FXML private TextField txtCognomeMds;
	@FXML private TextField txtUsernameMds;
	@FXML private PasswordField txtPasswordMds;
	@FXML private TextField txtEmailMds;
	@FXML private TextField txtTelefonoMds;
	
	//hashmap dei contenuti delle text
	private Map<String, String> parametriDB = new HashMap<>();
	private Map<String, String> parametriMds = new HashMap<>();
	
	
	private FC frontController = FC.getInstance();
	
	private FrameRequest richiesta;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.databasePane.setVisible(true);
        this.managerSistemaPane.setVisible(false);
	}
	
	@FXML protected void btnAvantiClicked(MouseEvent event) {
		//controllo la validità delle textfield
		if((this.txtTipoDB.getText().length() != 0)  &&
			(this.txtPercorsoDB.getText().length() != 0)  && 
			(this.txtNomeDB.getText().length() != 0)  &&
			(this.txtUsernameDB.getText().length() != 0)  &&
			(this.txtPasswordDB.getText().length() != 0)  
		) {
			//copio il contenuto delle textfield nell'hashmap parametri
			parametriDB.put("txtTipoDB", txtTipoDB.getText());
			parametriDB.put("txtPercorsoDB", txtPercorsoDB.getText());
			parametriDB.put("txtNomeDB", txtNomeDB.getText());
			parametriDB.put("txtUsernameDB", txtUsernameDB.getText());
			parametriDB.put("txtPasswordDB", txtPasswordDB.getText());
			
			this.richiesta = new FrameRequest(parametriDB,"testConnessioneDB");
			boolean res = (boolean) this.frontController.eseguiRichiesta(richiesta);
			
			//se la connessione al db è andata a buon fine procedi
			if(res){
				this.databasePane.setVisible(false);
		        this.managerSistemaPane.setVisible(true);
			}
			else
				System.out.println("Connessione al DB fallita");
		}
		else {
			System.out.println("Campi vuoti o errati");
		}
        
	}
	
	@FXML protected void btnIndietroClicked(MouseEvent event) {
		  this.databasePane.setVisible(true);
	      this.managerSistemaPane.setVisible(false);
		
	}
	
	@FXML protected void btnConfermaConfigurazione(MouseEvent event) {
		//controllo la validità delle textfield
		if((this.txtNomeMds.getText().length() != 0)  && 
			(this.txtCognomeMds.getText().length() != 0)  &&
			(this.txtUsernameMds.getText().length() != 0)  &&
			(this.txtPasswordMds.getText().length() != 0)  &&
			(this.txtEmailMds.getText().length() != 0)  &&
			(this.txtTelefonoMds.getText().length() != 0)  
		) {
			//richiesta per creare il Manager di Sistema
			//copio il contenuto delle textfield nell'hashmap parametri
			parametriMds.put("txtNomeMds", txtNomeMds.getText());
			parametriMds.put("txtCognomeMds", txtCognomeMds.getText());
			parametriMds.put("txtUsernameMds", txtUsernameMds.getText());
			parametriMds.put("txtPasswordMds", txtPasswordMds.getText());
			parametriMds.put("txtEmailMds", txtEmailMds.getText());
			parametriMds.put("txtTelefonoMds", txtTelefonoMds.getText());
			
			this.richiesta = new FrameRequest(parametriMds,"nuovoMDS");
			Object res = this.frontController.eseguiRichiesta(richiesta);
		}
		else {
			System.out.println("Campi vuoti o errati");
		}
		
	}
	

}
