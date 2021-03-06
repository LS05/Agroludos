package agroludos.presentation.views.partecipante;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CertificatoTO;
import agroludos.to.PartecipanteTO;

/**
 * Gestisce la view per la visualizzazione del certificato SRC
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
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
		if(mainTO instanceof PartecipanteTO){
			this.parTO = (PartecipanteTO)mainTO;
			StringBuffer nomeCognome = new StringBuffer(80);
			nomeCognome.append(parTO.getNome());
			nomeCognome.append(" ");
			nomeCognome.append(parTO.getCognome());
			this.lblNomeCognome.setText(nomeCognome.toString());
			this.lblDataSrc.setText(parTO.getDataSRC().toString());

			this.risposta = this.getRisposta();
			this.richiesta = this.getRichiesta(this.parTO, "getCertificatoSRC", this.viewName);
			this.eseguiRichiesta(this.richiesta, this.risposta);
		}
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
		String commandName = request.getCommandName();

		if( commandName.equals(this.getCommandName("getCertificatoSRC") )){
			Object res = response.getRespData();
			if(res instanceof CertificatoTO){
				CertificatoTO certificato = (CertificatoTO)res;
				this.txtAreaCertificato.setText(certificato.getCertificatoCont());
			}
		}
	}
}