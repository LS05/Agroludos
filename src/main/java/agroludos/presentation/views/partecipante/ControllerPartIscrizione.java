package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.PartecipanteTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ControllerPartIscrizione extends AgroludosController implements Initializable{
	private String viewName;

	private @FXML Label lblNomeCompetizione;
	private @FXML Label lblTipoCompetizione;
	private @FXML Label lblData;
	private @FXML Label lblNMax;
	private @FXML Label lblNMin;
	private @FXML Label lblCosto;
	private @FXML Label lblCostoTot;
	private @FXML Button btnSelezionaOptional;

	private CompetizioneTO mainComp;
	private IscrizioneTO mainIscr;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ResourceBundle res;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.res = resources;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.mainIscr = ((IscrizioneTO) mainTO);
			this.mainComp = this.mainIscr.getCompetizione();
			this.mainIscr.setPartecipante((PartecipanteTO)getUtente());
			this.lblNomeCompetizione.setText(this.mainComp.getNome());
			this.lblTipoCompetizione.setText(this.mainComp.getTipoCompetizione().getNome());
			this.lblData.setText(this.mainComp.getData().toString());
			this.lblNMax.setText(((Integer)this.mainComp.getNmax()).toString());
			this.lblNMin.setText(((Integer)this.mainComp.getNmin()).toString());
			this.lblCosto.setText(this.mainComp.getCosto().toString());
			this.btnSelezionaOptional.setText(this.res.getString("key67"));
			if( this.mainIscr.getCosto() != null && this.mainIscr.getCosto() > 0 ){
				DecimalFormat df = new DecimalFormat("#.00");
				double costo = this.mainIscr.getCosto();
				this.lblCostoTot.setText(df.format(costo));
				if(this.mainIscr.getAllOptionals().size() > 0){
					this.btnSelezionaOptional.setText(this.res.getString("key98"));
				}
			} else {
				this.lblCostoTot.setText(this.mainComp.getCosto().toString());
			}

			if(this.mainComp.getAllOptionals().isEmpty())
				this.btnSelezionaOptional.setDisable(true);
		}
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@FXML protected void btnAnnullaClicked(MouseEvent event){
		this.close();
	}

	@FXML protected void btnSelezOptClicked(MouseEvent event){
		nav.setVista("selezionaOptionalPart", this.mainIscr);
	}

	@FXML protected void btnIscrivitiClicked(MouseEvent event){
		this.mainIscr.setData(new Date());
		this.mainIscr.setCompetizione(this.mainComp);
		Double totale = (double) 0;
		for(OptionalTO optTO: this.mainIscr.getAllOptionals())
			totale = totale + optTO.getCosto();
		totale = totale + this.mainComp.getCosto();
		this.mainIscr.setCosto(totale);

		this.richiesta = this.getRichiesta(this.mainIscr, "inserisciIscrizione", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.close();
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
	}
}