package agroludos.presentation.views.mdc;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;

public class ControllerMotivoEliminazione extends AgroludosController{

	private String viewName;
	@FXML private Button btnInvia;
	@FXML private TextArea txtMotivazioni;
	private IscrizioneTO iscTO;
	private EmailTO mail;
	private AgroResponse risposta;
	private AgroRequest richiesta;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.iscTO = (IscrizioneTO) mainTO;
			//creo l'email per il partecipante
			mail = toFact.createEmailTO();
			mail.setOggetto("Iscrizione annullata");
			mail.addDestinatario(iscTO.getPartecipante());
		}
	}

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;
		this.txtMotivazioni.setText("");
		
		final Stage stage = this.getStage(this.viewName);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				invia();
			}
		});
	}

	@FXML protected void btnInvia(MouseEvent event) {	
		invia();	
	}

	private void invia(){
		mail.setMessage(iscTO.getPartecipante().getUsername() + " abbiamo annullato l'iscrizione "
				+ "alla competizione " + iscTO.getCompetizione().getNome()
				+ "per i seguenti motivi: " + this.txtMotivazioni.getText());
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
		// TODO Auto-generated method stub

	}

}
