package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsNuovoTipoOptional extends AgroludosController implements Initializable{
	private String viewName;
	private boolean flagError;

	private @FXML TextField txtNomeTipo;
	private @FXML Label lblNomeError;

	private ResourceBundle res;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.flagError = false;
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		this.flagError = false;
		this.txtNomeTipo.setText("");
		this.lblNomeError.setVisible(false);
	}

	@FXML protected void confermaNuovoTipoOptional(MouseEvent event) {
		TipoOptionalTO tipoOtp = toFact.createTipoOptionalTO();
		tipoOtp.setNome(this.txtNomeTipo.getText());

		this.richiesta = this.getRichiesta(tipoOtp, "inserisciTipoOptional", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		if(!this.flagError){
			SuccessTO msgNuovoOpt = toFact.createSuccessTO();
			msgNuovoOpt.setMessage(this.res.getString("key166"));
			nav.setVista("successDialog", msgNuovoOpt);
		}

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( reqProperties.getProperty("inserisciTipoOptional") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;
				this.flagError = true;
				if(errors.hasError(rulesProperties.getProperty("nomeKey"))){
					String nomeKey = rulesProperties.getProperty("nomeKey");
					this.lblNomeError.setVisible(true);
					this.lblNomeError.setText(errors.getError(nomeKey));
				} 
			}
		}
	}
}