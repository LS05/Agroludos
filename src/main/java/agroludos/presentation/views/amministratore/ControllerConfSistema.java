package agroludos.presentation.views.amministratore;

import java.net.URL;
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
	
	private FC frontController = FC.getInstance();
	
	private FrameRequest richiesta;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.databasePane.setVisible(true);
        this.managerSistemaPane.setVisible(false);
	}
	
	@FXML protected void btnAvantiClicked(MouseEvent event) {
		//controllo la validità delle textfield
		if((this.txtPercorsoDB.getText().length() != 0)  && 
			(this.txtNomeDB.getText().length() != 0)  &&
			(this.txtUsernameDB.getText().length() != 0)  &&
			(this.txtPasswordDB.getText().length() != 0)  
		) {
			this.databasePane.setVisible(false);
	        this.managerSistemaPane.setVisible(true);
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
			this.richiesta = new FrameRequest("confermaConfigurazione");
			Object res = this.frontController.eseguiRichiesta(richiesta);
		}
		else {
			System.out.println("Campi vuoti o errati");
		}
		
	}
	

}
