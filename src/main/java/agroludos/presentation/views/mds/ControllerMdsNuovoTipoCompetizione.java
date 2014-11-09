package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.SuccessMessageTO;
import agroludos.to.TipoCompetizioneTO;

public class ControllerMdsNuovoTipoCompetizione extends AgroludosController implements Initializable{
	private String viewName;
	private boolean flagError;

	private @FXML TextField txtNomeTipo;
	private @FXML Label lblNomeError;
	private @FXML TextArea txtAreaDesc;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ResourceBundle res;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;	
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		this.flagError = false;
		this.txtNomeTipo.setText("");
		this.lblNomeError.setVisible(false);
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.flagError = false;
	}

	@FXML protected void confermaNuovoTipoCompetizione(MouseEvent event) {
		TipoCompetizioneTO tipoCpt = toFact.createTipoCompetizioneTO();
		tipoCpt.setNome(this.txtNomeTipo.getText());
		tipoCpt.setDescrizione(this.txtAreaDesc.getText());

		this.richiesta = this.getRichiesta(tipoCpt, "inserisciTipoCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		if(!this.flagError){
			SuccessMessageTO msgNuovoOpt = toFact.createSuccMessageTO();
			msgNuovoOpt.setMessage(this.res.getString("key129"));
			this.setVista("messageDialog", msgNuovoOpt);
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("inserisciTipoCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;
				this.flagError = true;
				if(errors.hasError(this.getError("nomeKey"))){
					String nomeKey = this.getError("nomeKey");
					this.lblNomeError.setVisible(true);
					this.lblNomeError.setText(errors.getError(nomeKey));
				} 
			}
		}
	}
}