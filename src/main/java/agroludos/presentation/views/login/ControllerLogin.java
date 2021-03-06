package agroludos.presentation.views.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.SuccessMessageTO;
import agroludos.to.UtenteTO;

/**
 * La classe gestisce la view di Login
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerLogin extends AgroludosController implements Initializable{

	private String viewName;

	@FXML private Button btnLogin;
	@FXML private Button btnPswDimenticata;
	@FXML private Button btnRegistrati;
	@FXML private Pane agroLogoPane;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblErroreLogin;

	private ResourceBundle res;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;		
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {

	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.lblErroreLogin.setVisible(false);

		final Stage stage = this.getStage(this.viewName);
		//aggiungo per chiudere il programma
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				risposta = getRisposta();
				richiesta = getRichiesta("chiusura", viewName);
				eseguiRichiesta(richiesta, risposta);
			}
		});

		this.agroLogoPane.setFocusTraversable(true);
	}

	@FXML protected void txtKeyPressed(KeyEvent evt) {
		if (evt.getCode() == KeyCode.ENTER){
			eseguiLogin();
		}
	}

	@FXML protected void btnLogin(MouseEvent event) {	
		eseguiLogin();
	}

	/**
	 * popola il to Utente e esegue la richiesta di autenticazione
	 */
	private void eseguiLogin() {
		this.lblErroreLogin.setVisible(false);

		UtenteTO uto = toFact.createUTO();
		uto.setUsername(this.txtUsername.getText());
		uto.setPassword(this.txtPassword.getText());
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(uto, "autenticazioneUtente", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		Object res = this.risposta.getRespData();
		if(res instanceof UtenteTO){
			this.close();
		}
	}

	/**
	 * mostra la view passwordDimenticata
	 * @param event
	 */
	@FXML protected void btnPswDimenticata(MouseEvent event) {
		this.setVista("passwordDimenticata");
	}

	/**
	 * mostra la view per la registrazione
	 * @param event
	 */
	@FXML protected void btnRegistrati(MouseEvent event) {
		this.setVista("nuovaRegistrazione");
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals(this.getCommandName("inserisciPartecipante") )){
			Object res = response.getRespData();
			if(res instanceof PartecipanteTO){
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.res.getString("key152"));
				this.setVista("messageDialog", succMessage);
			}
		}else if(commandName.equals(this.getCommandName("autenticazioneUtente") )){
			Object res = response.getRespData();
			if(res instanceof String){
				String errMsg = (String)res;
				this.lblErroreLogin.setVisible(true);
				this.lblErroreLogin.setText(errMsg);
			}
		}else if(commandName.equals(this.getCommandName("nuovoManagerDiSistema") )){
			Object res = response.getRespData();
			if(res instanceof UtenteTO){
				closeVista("configurazione");
			}
		}
	}
}