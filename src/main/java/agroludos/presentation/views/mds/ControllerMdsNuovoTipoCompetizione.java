package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoCompetizioneTO;

public class ControllerMdsNuovoTipoCompetizione extends AgroludosController implements Initializable{
	private String viewName;
	
	private @FXML TextField txtNomeTipo;
	private @FXML TextArea txtAreaDesc;
	
	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ResourceBundle res;
	
	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;	
	}

	@FXML protected void confermaNuovoTipoCompetizione(MouseEvent event) {
		TipoCompetizioneTO tipoCpt = toFact.createTipoCompetizioneTO();
		tipoCpt.setNome(this.txtNomeTipo.getText());
		tipoCpt.setDescrizione(this.txtAreaDesc.getText());

		this.richiesta = this.getRichiesta(tipoCpt, "inserisciTipoCompetizione", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		SuccessTO msgNuovoOpt = toFact.createSuccessTO();
		msgNuovoOpt.setMessage(this.res.getString("key129"));
		nav.setVista("successDialog", msgNuovoOpt);
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		
		// TODO Auto-generated method stub
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}