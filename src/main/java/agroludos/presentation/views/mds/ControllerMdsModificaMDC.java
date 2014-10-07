package agroludos.presentation.views.mds;

import java.util.List;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoUtenteTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerMdsModificaMDC extends AgroludosController{
	private String viewName;

	@FXML private TextField txtUsername;
	@FXML private TextField txtEmail;
	@FXML private TextField txtNome;
	@FXML private TextField txtCognome;
	@FXML private ComboBox<String> cmbStato;
	@FXML private Label lblMessaggioModifica;

	private List<StatoUtenteTO> listStatiUtente;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	private ManagerDiCompetizioneTO mdcTO;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
		this.lblMessaggioModifica.setVisible(false);
		Scene scene = nav.getStage(viewName).getScene();
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(lblMessaggioModifica.isVisible())
					lblMessaggioModifica.setVisible(false);
			}
		});
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.lblMessaggioModifica.setVisible(false);

		this.mdcTO = (ManagerDiCompetizioneTO)mainTO;
		this.txtUsername.setText(this.mdcTO.getUsername());
		this.txtCognome.setText(this.mdcTO.getCognome());
		this.txtNome.setText(this.mdcTO.getNome());
		this.txtEmail.setText(this.mdcTO.getEmail());

		this.richiesta = this.getRichiesta("getAllStatoUtente", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listStati = FXCollections.observableArrayList();
		for(StatoUtenteTO stato : this.listStatiUtente){
			listStati.add(stato.getNome());
		}
		this.cmbStato.setItems(listStati);
		this.cmbStato.setValue(this.mdcTO.getStatoUtente().getNome());
	}

	@FXML public void confermaModificaManagerDiCompetizion(MouseEvent event){
		this.mdcTO.setNome(this.txtNome.getText());
		this.mdcTO.setCognome(this.txtCognome.getText());
		this.mdcTO.setUsername(this.txtUsername.getText());
		this.mdcTO.setEmail(this.txtEmail.getText());

		Integer statoSel = this.cmbStato.getSelectionModel().getSelectedIndex();
		StatoUtenteTO stato = this.listStatiUtente.get(statoSel);
		this.mdcTO.setStatoUtente(stato);

		this.richiesta = this.getRichiesta(mdcTO, "modificaManagerDiCompetizione", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		if(this.risposta.getRespData() instanceof ManagerDiCompetizioneTO){
			this.lblMessaggioModifica.setVisible(true);
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.reqProperties.getProperty("getAllStatoUtente") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoUtenteTO> listStati = (List<StatoUtenteTO>)res;
				this.listStatiUtente = listStati;
			}
		}

	}
}