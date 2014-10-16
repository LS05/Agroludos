package agroludos.presentation.views.partecipante;

import java.util.List;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.table.TableCompetizioni;
import agroludos.presentation.views.table.TableIscrizioni;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.PartecipanteTO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerPartecipanteMain extends ControllerUtenti{
	private String viewName;

	@FXML private GridPane paneCompetizioni;
	@FXML private GridPane paneIscrizioni;
	@FXML private Button btnGestManComp; 
	@FXML private Button btnGestIscrizioni;
	private TableIscrizioni tableIscrizioni;
	private TableCompetizioni tableCompetizioni;

	private PartecipanteTO currUser;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private List<CompetizioneTO> listComp;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		//TODO Richiesta iniziale per verificare la validità del
		//certificato SRC.

		this.currUser = (PartecipanteTO)utente;
		this.tableIscrizioni = new TableIscrizioni();
		this.tableCompetizioni = new TableCompetizioni();
		this.tableIscrizioni.setAll(this.currUser.getAllIscrizioni());
		this.paneIscrizioni.add(this.tableIscrizioni, 0, 1);
		this.richiesta = this.getRichiesta("getCompetizioniAttive", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		this.tableCompetizioni.setAll(this.listComp);
		this.paneCompetizioni.add(this.tableCompetizioni, 0, 1);

		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);
	}

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);
	}

	@FXML protected void btnGestIscrizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(false);
		this.paneIscrizioni.setVisible(true);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.reqProperties.getProperty("getCompetizioniAttive") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				this.listComp = (List<CompetizioneTO>)res;
			}
		}
	}
}