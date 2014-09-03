package agroludos.presentation.views.amministratore;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.utility.SecurePassword;
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

import java.security.NoSuchAlgorithmException;

public class ControllerConfSistema extends AgroludosController implements Initializable{

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
	
	private AgroRequest richiesta;
	
	private AgroResponse risposta;

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

		//DA ELIMINARE
		this.txtServerDB.setText("localhost"); 
		this.txtPortaDB.setText("3306"); 
		this.txtNomeDB.setText("agroludos");
		this.txtUsernameDB.setText("root");
		this.txtPasswordDB.setText("root");  
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

			//copio il contenuto delle textfield nell'hashmap parametri
			parametriDB.put("tipo", this.cmbTipoDB.getValue());
			parametriDB.put("server", txtServerDB.getText());
			parametriDB.put("porta", txtPortaDB.getText());
			parametriDB.put("nome", txtNomeDB.getText());
			parametriDB.put("username", txtUsernameDB.getText());
			parametriDB.put("password", this.txtPasswordDB.getText());

			this.richiesta = reqFact.createDataRequest(parametriDB, "confermaConfigurazione");
			this.risposta = respFact.createResponse();
			frontController.eseguiRichiesta(this.richiesta, this.risposta);
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
			//sicurezza password
			String securePassword = null;
			try {
				securePassword = SecurePassword.stringToMD5(this.txtPasswordMds.getText());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//copio il contenuto delle textfield nell'hashmap parametri
			parametriMds.put("nome", txtNomeMds.getText());
			parametriMds.put("cognome", txtCognomeMds.getText());
			parametriMds.put("username", txtUsernameMds.getText());
			parametriMds.put("password", securePassword);
			parametriMds.put("email", txtEmailMds.getText());
			parametriMds.put("telefono", txtTelefonoMds.getText());

			this.richiesta = reqFact.createDataRequest(parametriMds, "nuovoManagerDiSistema");
			boolean res = (boolean)frontController.eseguiRichiesta(richiesta);
			//se non ci sono errori mostra la finestra di login
			if(res){
				System.out.println("Manager di Sistema inserito correttamente");
				nav.setVista("login");
			}
			else
				System.out.println("Inserimento fallito");
		}
		else {
			System.out.println("Campi vuoti o errati");
		}

	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("confermaConfigurazione")){
			boolean res = (Boolean)response.getRespData();
			if(res){
				this.databasePane.setVisible(false);
				this.managerSistemaPane.setVisible(true);
			} else
				System.out.println("Connessione al DB fallita");
		}
	}
}