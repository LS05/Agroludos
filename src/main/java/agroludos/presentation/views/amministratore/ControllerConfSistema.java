package agroludos.presentation.views.amministratore;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.DatabaseTO;
import agroludos.to.ManagerDiSistemaTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerConfSistema extends AgroludosController {

	private final String fromName = "confSistemaController";
	
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

	DatabaseTO dbto = null;

	ManagerDiSistemaTO mdsto = null;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	private ObservableList<String> listaTipiDB;

	@Override
	public void initializeView() {
		this.mdsto = toFact.createMdSTO();
		this.dbto = toFact.createDatabaseTO();
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

	/**
	 * 
	 * @param event
	 */
	@FXML protected void btnAvantiClicked(MouseEvent event) {	
		System.out.println("INSERISCI CONF");
		//copio il contenuto delle textfield nell'hashmap parametri
		this.dbto.setTipo(this.cmbTipoDB.getValue());
		this.dbto.setServer(this.txtServerDB.getText());
		this.dbto.setPorta(this.txtPortaDB.getText());
		this.dbto.setNome(this.txtNomeDB.getText());
		this.dbto.setUsername(this.txtUsernameDB.getText());
		this.dbto.setPassword(this.txtPasswordDB.getText());
		this.richiesta = this.getRichiesta(this.dbto, "inserisciConfigurazione", this.fromName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
	}

	/**
	 * 
	 * @param event
	 */
	@FXML protected void btnIndietroClicked(MouseEvent event) {
		this.databasePane.setVisible(true);
		this.managerSistemaPane.setVisible(false);
	}

	/**
	 * 
	 * @param event
	 */
	@FXML protected void btnConfermaConfigurazione(MouseEvent event) {
		this.mdsto.setNome(txtNomeMds.getText());
		this.mdsto.setCognome(txtCognomeMds.getText());
		this.mdsto.setUsername(txtUsernameMds.getText());
		this.mdsto.setPassword(this.txtPasswordMds.getText());
		this.mdsto.setEmail(txtEmailMds.getText());
		//TODO manca il telefono
		this.richiesta = this.getRichiesta(this.mdsto, "nuovoManagerDiSistema", this.fromName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("inserisciConfigurazione")){
			boolean res = (Boolean)response.getRespData();
			if(res){
				this.databasePane.setVisible(false);
				this.managerSistemaPane.setVisible(true);
			}
		}
		if(request.getCommandName().equals("nuovoManagerDiSistema")){
			boolean res = (Boolean)response.getRespData();
			if(res){
				nav.setVista("login");
			}
		}
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
		
	}
}