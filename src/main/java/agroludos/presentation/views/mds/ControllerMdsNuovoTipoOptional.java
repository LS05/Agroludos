package agroludos.presentation.views.mds;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsNuovoTipoOptional extends AgroludosController{
	private String viewName;
	private @FXML TextField txtNomeTipo;
	
	private @FXML Label lblNomeError;
	
	private AgroRequest richiesta;
	private AgroResponse risposta;

	@FXML protected void confermaNuovoTipoOptional(MouseEvent event) {
		TipoOptionalTO tipoOtp = toFact.createTipoOptionalTO();
		tipoOtp.setNome(this.txtNomeTipo.getText());

		this.richiesta = this.getRichiesta(tipoOtp, "inserisciTipoOptional", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		
		// TODO Auto-generated method stub
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		
		lblNomeError.setVisible(false);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}