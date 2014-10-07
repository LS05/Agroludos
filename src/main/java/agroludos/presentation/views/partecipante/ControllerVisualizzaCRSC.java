package agroludos.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;

public class ControllerVisualizzaCRSC extends AgroludosController {
	private String viewName;

	@FXML private Label lblNomeCognome;
	@FXML private Label lblDataSrc;
	@FXML private TextArea txtAreaCertificato;

	private PartecipanteTO parTO;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.parTO = (PartecipanteTO)mainTO;
		StringBuffer nomeCognome = new StringBuffer();
		nomeCognome.append(parTO.getNome());
		nomeCognome.append(" ");
		nomeCognome.append(parTO.getCognome());
		this.lblNomeCognome.setText(nomeCognome.toString());
		this.lblDataSrc.setText(parTO.getDataSRC().toString());

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.parTO, "getCertificatoSRC", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		

	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;

	}
	
	@Override
	protected String getViewName() {
		return this.viewName;
	}
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("getCertificatoSRC")){
			Object res = response.getRespData();
			if(res instanceof String)
				this.txtAreaCertificato.setText((String) res);
		}
	}
}