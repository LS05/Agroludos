package agroludos.presentation.views.mds;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;

public class ControllerMdsVisualizzaCRSC extends AgroludosController {

	@FXML private Label lblNomeCognome;
	@FXML private Label lblDataSrc;
	private String certificatoSRC;
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		PartecipanteTO part = (PartecipanteTO)mainTO;
		StringBuffer nomeCognome = new StringBuffer();
		nomeCognome.append(part.getNome());
		nomeCognome.append(" ");
		nomeCognome.append(part.getCognome());
		this.lblNomeCognome.setText(nomeCognome.toString());
		this.lblDataSrc.setText(part.getDataSRC().toString());
	}

	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
	}
}