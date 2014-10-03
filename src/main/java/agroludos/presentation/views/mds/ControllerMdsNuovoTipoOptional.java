package agroludos.presentation.views.mds;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsNuovoTipoOptional extends AgroludosController{
	private String nameView;
	private @FXML TextField txtNomeTipo;
	private @FXML Label lblMsgNuovoTipo;
	private AgroRequest richiesta;
	private AgroResponse risposta;

	@FXML protected void confermaNuovoTipoOptional(MouseEvent event) {
		TipoOptionalTO tipoOtp = toFact.createTipoOptionalTO();
		tipoOtp.setNome(this.txtNomeTipo.getText());

		this.richiesta = this.getRichiesta(tipoOtp, "inserisciTipoOptional", this.nameView);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void initializeView(String viewName) {
		this.nameView = viewName;
		Scene scene = nav.getStage(nameView).getScene();
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(lblMsgNuovoTipo.isVisible())
					lblMsgNuovoTipo.setVisible(false);
			}
		});
	}

	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}