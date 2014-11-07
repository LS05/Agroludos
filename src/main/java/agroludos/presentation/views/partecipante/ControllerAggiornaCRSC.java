package agroludos.presentation.views.partecipante;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.datepicker.AgroDatePicker;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;

public class ControllerAggiornaCRSC extends AgroludosController {
	private String viewName;

	@FXML private Label lblSrc;
	@FXML private GridPane paneAggiornaSrc;
	@FXML private Pane paneDataSrc;
	private AgroDatePicker dataSrcPicker;

	private File fileSrc;
	private FileChooser fileChooser;
	private PartecipanteTO parTO;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof PartecipanteTO){
			this.parTO = (PartecipanteTO)mainTO;
			this.fileChooser = new FileChooser();
		}
	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.dataSrcPicker = new AgroDatePicker();
		this.paneDataSrc.getChildren().add(this.dataSrcPicker.getDatePicker());
	}

	@FXML protected void btnAggiornaClicked(MouseEvent event){
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.parTO, "modificaPartecipante", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@FXML protected void btnCaricaClicked(MouseEvent event){
		File file = this.fileChooser.showOpenDialog(this.getStage(this.viewName));
		if (file != null) {
			this.fileSrc = file;
			this.lblSrc.setText(this.fileSrc.getName());
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		Object res = response.getRespData();

		if( commandName.equals(this.getCommandName("modificaPartecipante") )){
			if(res instanceof String){

			}
		}
	}
}