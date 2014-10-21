package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerPartIscrizione extends AgroludosController implements Initializable {
	private String viewName;
	
	private @FXML Label lblNomeCompetizione;
	private @FXML Label lblTipoCompetizione;
	private @FXML Label lblData;
	private @FXML Label lblNMax;
	private @FXML Label lblNMin;
	private @FXML Label lblCosto;
	private @FXML Label lblCostoTot;
	
	private CompetizioneTO mainComp;
	
	private IscrizioneTO mainIscr;
	
	private AgroRequest richiesta;
	private AgroResponse risposta;
	
	private ResourceBundle resources;

	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.resources = res;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof CompetizioneTO){
			this.mainComp = ((CompetizioneTO) mainTO);
			this.mainIscr = toFact.createIscrizioneTO();
			this.mainIscr.setCompetizione(this.mainComp);
			this.mainIscr.setPartecipante(getUtente());
			this.lblNomeCompetizione.setText(this.mainComp.getNome());
			this.lblTipoCompetizione.setText(this.mainComp.getTipoCompetizione().getNome());
			this.lblData.setText(this.mainComp.getData().toString());
			this.lblNMax.setText(((Integer)this.mainComp.getNmax()).toString());
			this.lblNMin.setText(((Integer)this.mainComp.getNmin()).toString());
			this.lblCosto.setText(this.mainComp.getCosto().toString());
			this.lblCostoTot.setText(this.mainComp.getCosto().toString());
		}
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}
	
	@FXML protected void btnSelezOptClicked(MouseEvent event){
		nav.setVista("selezionaOptionalPart", this.mainIscr);
	}
	
	@FXML protected void btnIscrivitiClicked(MouseEvent event){
		this.richiesta = this.getRichiesta(this.mainIscr, "inserisciIscrizione", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}

}