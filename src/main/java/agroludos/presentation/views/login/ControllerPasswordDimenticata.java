package agroludos.presentation.views.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.MessageTO;
import agroludos.to.SuccessMessageTO;
import agroludos.to.UtenteTO;

public class ControllerPasswordDimenticata extends AgroludosController implements Initializable{
	
	private String viewName;
	
	@FXML private Button btnRichiedi;
	@FXML private Pane agroLogoPane;
	@FXML private TextField txtUsername;
	@FXML private TextField txtEmail;

	@FXML private Label lblErroreLogin;
	
	private ResourceBundle res;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	
	private UtenteTO uTO;

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;

		this.txtUsername.clear();
		this.txtUsername.setPromptText(this.res.getString("key74"));
		this.txtEmail.clear();
		this.txtEmail.setPromptText(this.res.getString("key4"));
		this.agroLogoPane.setFocusTraversable(true);
		this.uTO = toFact.createUTO();

	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
	}
	
	@Override
	protected String getViewName() {
		return this.viewName;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;		
	}
	
	@FXML protected void txtKeyPressed(javafx.scene.input.KeyEvent evt) {
		if (evt.getCode() == KeyCode.ENTER)
			eseguiRipristino();
	}

	@FXML protected void btnRichiedi(MouseEvent event) {	
		this.uTO.setUsername(this.txtUsername.getText());
		this.uTO.setEmail(this.txtEmail.getText());
		eseguiRipristino();
	}
	
	private void eseguiRipristino() {	
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.uTO, "passwordDimenticata", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals(this.getCommandName("passwordDimenticata") )){
			Object res = response.getRespData();
			if(res instanceof UtenteTO){
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.res.getString("key155"));
				this.setVista("messageDialog",succMessage);	
				this.close();
			}else if(res instanceof String){
				MessageTO errMessage = toFact.createErrMessageTO();
				errMessage.setMessage((String) res);
				this.setVista("messageDialog",errMessage);
			}
		}
	}


}