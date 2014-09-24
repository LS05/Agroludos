package agroludos.presentation.views.login;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import agroludos.to.UtenteTO;

public class ControllerLogin extends AgroludosController{
	
	
	
	@FXML private Button btnLogin;
	@FXML private Button btnPswDimenticata;
	@FXML private Button btnRegistrati;
	@FXML private Pane agroLogoPane;
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	@FXML private Label lblErroreLogin;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	@FXML protected void txtKeyPressed(javafx.scene.input.KeyEvent evt) {
		if (evt.getCode() == KeyCode.ENTER)
			eseguiLogin();
	}

	@FXML protected void btnLogin(MouseEvent event) {	
		eseguiLogin();
	}
	
	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;

		final Stage stage = nav.getStage(nameView);
		final String mainView = nameView;
		//aggiungo l'evento close vista quando si chiude lo stage
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				//TODO eliminare stampa
				nav.closeVista(mainView);
			}
		}); 
		
		
		this.agroLogoPane.setFocusTraversable(true);
		this.txtUsername.setText("agroludos");
		this.txtPassword.setText("agroludos");
	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
	}

	protected void eseguiLogin() {	
		UtenteTO uto = toFact.createUTO();
		uto.setUsername(this.txtUsername.getText());
		uto.setPassword(this.txtPassword.getText());
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(uto, "autenticazioneUtente", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@FXML protected void btnPswDimenticata(MouseEvent event) {
		//TODO Gestire Servizio
	}

	@FXML protected void btnRegistrati(MouseEvent event) {
		this.risposta = respFact.createResponse();		
		this.richiesta = this.getRichiesta("nuovaRegistrazione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("autenticazioneUtente")){
			Object res = response.getRespData();
			if(res instanceof String){
				String errMsg = (String)res;
				this.lblErroreLogin.setVisible(true);
				this.lblErroreLogin.setText(errMsg);
			}
		}else if(request.getCommandName().equals("sessione.managerDiSistema") ||
				request.getCommandName().equals("sessione.managerDiCompetizione") ||
				request.getCommandName().equals("sessione.partecipante")){
			//TODO exception??
			System.out.println("errore nella creazione della sessione");
			
		}
	}
	
	private String nameView;
	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}
}