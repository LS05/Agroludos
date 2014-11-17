package agroludos.presentation.views.mdc;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.EmailTO;

/**
 * gestisce la view per l'inserimento di una motivazione per l'eliminazione di una iscrizione
 * da una competizione da parte di un manager di competizione
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerMotivoEliminazione extends AgroludosController{

	private String viewName;

	@FXML private TextArea txtMotivazioni;

	private EmailTO mail;
	private AgroResponse risposta;
	private AgroRequest richiesta;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof EmailTO){
			this.mail = (EmailTO) mainTO;
		}
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
		this.txtMotivazioni.setText("");

		final Stage stage = this.getStage(this.viewName);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				invia();
			}
		});
	}

	@FXML protected void btnInviaClicked(MouseEvent event) {	
		invia();	
	}

	/**
	 * popola l'email TO e effettua la richiesta di invio mail tramite il gestore mail
	 */
	private void invia(){
		String motivazione = this.txtMotivazioni.getText();
		String message = this.mail.getMessage();
		StringBuilder mail = new StringBuilder(300);
		mail.append(message);
		mail.append(motivazione);
		this.mail.setMessage(mail.toString());
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.mail, "inviaMail", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);
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