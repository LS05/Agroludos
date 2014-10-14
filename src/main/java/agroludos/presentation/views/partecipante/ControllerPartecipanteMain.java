package agroludos.presentation.views.partecipante;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.utenti.ControllerUtenti;

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


	@Override
	public void initializeView(String viewName) {
		
		//TODO Richiesta iniziale per verificare la validità del
		//certificato SRC.
		this.viewName = viewName;

		//TODO Da testare
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

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		
	}
}