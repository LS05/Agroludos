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
import agroludos.to.ErrorTO;
import agroludos.to.SuccessTO;
import agroludos.to.UtenteTO;

public class ControllerPasswordDimenticata extends AgroludosController implements Initializable{
	
	private String viewName;
	
	@FXML private Button btnRichiedi;
	@FXML private Pane agroLogoPane;
	@FXML private TextField txtUsername;

	@FXML private Label lblErroreLogin;
	
	private ResourceBundle res;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	
	private UtenteTO uTO;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		this.txtUsername.clear();
		this.txtUsername.setPromptText(this.res.getString("key74"));
		this.agroLogoPane.setFocusTraversable(true);
		this.uTO = toFact.createUTO();

	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
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
		eseguiRipristino();
	}
	
	private void eseguiRipristino() {	
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.uTO, "getUtenteByUsername", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		Object res = this.risposta.getRespData();
		if(res instanceof UtenteTO){			
			SuccessTO succMessage = toFact.createSuccessTO();
			succMessage.setMessage(this.res.getString("key155"));
			nav.setVista("successDialog",succMessage);
			this.close();
		}
	}
	

	
	

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals(reqProperties.getProperty("getUtenteByUsername") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){
				//TODO gestire 
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.res.getString("key156"));
				nav.setVista("successDialog",succMessage);
			}
		}
	}


}