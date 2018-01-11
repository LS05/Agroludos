package agroludos.presentation.views.mds;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.TipoOptionalTO;

/**
 * Gestisce la view per l'inserimento di un nuovo tipo optional
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerMdsNuovoTipoOptional extends AgroludosController{
	private String viewName;

	private @FXML TextField txtNomeTipo;
	private @FXML Label lblNomeError;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
	}

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;
		this.txtNomeTipo.setText("");
		this.lblNomeError.setVisible(false);
	}

	/**
	 * inserisce i dati in un {@link TipoOptionalTO} e effettua la richiesta per 
	 * l'inserimento del nuovo tipooptional
	 * @param event
	 */
	@FXML protected void confermaNuovoTipoOptional(MouseEvent event) {
		TipoOptionalTO tipoOtp = toFact.createTipoOptionalTO();
		tipoOtp.setNome(this.txtNomeTipo.getText());

		this.richiesta = this.getRichiesta(tipoOtp, "inserisciTipoOptional", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("inserisciTipoOptional") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;
				if(errors.hasError(this.getError("nomeKey"))){
					String nomeKey = this.getError("nomeKey");
					this.lblNomeError.setVisible(true);
					this.lblNomeError.setText(errors.getError(nomeKey));
				} 
			}else if(res instanceof String){
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}
	}
}