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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.SuccessTO;
import agroludos.to.UtenteTO;

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
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		final Stage stage = nav.getStage(this.getViewName());
		final String view = this.viewName;
		//aggiungo l'evento close vista quando si chiude lo stage
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				//TODO eliminare stampa
				nav.closeVista(view);
				nav.termina();
			}
		}); 


		this.agroLogoPane.setFocusTraversable(true);
		this.txtUsername.setText("agroludos");
		this.txtPassword.setText("agroludos");
	}

	@FXML protected void txtKeyPressed(javafx.scene.input.KeyEvent evt) {
		if (evt.getCode() == KeyCode.ENTER)
			eseguiLogin();
	}

	@FXML protected void btnLogin(MouseEvent event) {	
		eseguiLogin();
	}

	private void eseguiLogin() {	
		UtenteTO uto = toFact.createUTO();
		uto.setUsername(this.txtUsername.getText());
		uto.setPassword(this.txtPassword.getText());
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(uto, "autenticazioneUtente", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		Object res = risposta.getRespData();
		if(res instanceof UtenteTO){
			this.close();
		}
	}

	@FXML protected void btnPswDimenticata(MouseEvent event) {
		nav.setVista("passwordDimenticata");
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		nav.setVista("nuovaRegistrazione");
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals(reqProperties.getProperty("inserisciPartecipante") )){
			Object res = response.getRespData();
			if(res instanceof PartecipanteTO){
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.res.getString("key152"));
				nav.setVista("successDialog",succMessage);
			}
		}else if(commandName.equals(reqProperties.getProperty("autenticazioneUtente") )){
			Object res = response.getRespData();
			if(res instanceof String){
				String errMsg = (String)res;
				this.lblErroreLogin.setVisible(true);
				this.lblErroreLogin.setText(errMsg);
			}
		}
	}
}