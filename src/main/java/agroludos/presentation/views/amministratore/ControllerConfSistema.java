package agroludos.presentation.views.amministratore;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import agroludos.presentation.fc.FC;
import agroludos.presentation.req.FrameRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ControllerConfSistema implements Initializable{
	@FXML private Button btnAvantiConf;
	@FXML private Button btnIndietroConf;
	@FXML private Button btnConfermaConfigurazione;
	@FXML private GridPane databasePane;
	@FXML private GridPane managerSistemaPane;

	//texfield DataBase
	@FXML private ComboBox<String> cmbTipoDB;
	@FXML private TextField txtServerDB;
	@FXML private TextField txtPortaDB;
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
	private ObservableList<String> listaTipiDB;




	public void initialize(URL arg0, ResourceBundle arg1) {
		this.listaTipiDB = FXCollections.observableArrayList();
		//aggiungo i tipi di db alla combobox
		this.listaTipiDB.add("mysql");
		this.listaTipiDB.add("oracle");
		this.listaTipiDB.add("postgress");
		this.cmbTipoDB.setItems(this.listaTipiDB);
		this.cmbTipoDB.setValue("mysql");

		this.databasePane.setVisible(true);
		this.managerSistemaPane.setVisible(false);
	}

	@FXML protected void btnAvantiClicked(MouseEvent event) {
		//controllo la validità delle textfield	

		if((this.cmbTipoDB.getValue().length() != 0)  &&
				(this.txtServerDB.getText().length() != 0)  && 
				(this.txtPortaDB.getText().length() != 0)  && 
				(this.txtNomeDB.getText().length() != 0)  &&
				(this.txtUsernameDB.getText().length() != 0)  &&
				(this.txtPasswordDB.getText().length() != 0)  
				) {

			//sicurezza password
			String securePassword = null;
			try {
				securePassword = this.stringToMD5(this.txtPasswordDB.getText());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//copio il contenuto delle textfield nell'hashmap parametri
			parametriDB.put("tipo", this.cmbTipoDB.getValue());
			parametriDB.put("server", txtServerDB.getText());
			parametriDB.put("porta", txtPortaDB.getText());
			parametriDB.put("nome", txtNomeDB.getText());
			parametriDB.put("username", txtUsernameDB.getText());
			parametriDB.put("password", securePassword);

			this.richiesta = new FrameRequest(parametriDB,"confermaConfigurazione");
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

	public String stringToMD5 (String str) throws NoSuchAlgorithmException{

		String securePassword = null;
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		//Add password bytes to digest
		md.update(str.getBytes());
		//Get the hash's bytes 
		byte[] bytes = md.digest();
		//This bytes[] has bytes in decimal format;
		//Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for(int i=0; i< bytes.length ;i++)
		{
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		//Get complete hashed password in hex format
		securePassword = sb.toString();

		return securePassword;

	}


}
