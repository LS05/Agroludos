package agroludos.presentation.views.partecipante;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.datepicker.AgroDatePicker;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.SuccessMessageTO;

/**
 * gestisce la view per l'aggiornamento del certificato SRC
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerAggiornaCSRC extends AgroludosController implements Initializable {
	private String viewName;

	@FXML private Label lblSrc;
	@FXML private Label lblSrcError;
	@FXML private Label lblDataSrcError;
	@FXML private Pane paneDataSrc;
	private AgroDatePicker dataSrcPicker;

	private File fileSrc;
	private FileChooser fileChooser;
	private PartecipanteTO parTO;

	private ResourceBundle res;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.res = res;
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof PartecipanteTO){
			this.parTO = (PartecipanteTO)mainTO;
			this.fileChooser = new FileChooser();
			this.lblDataSrcError.setVisible(false);
			this.lblSrcError.setVisible(false);
		}
	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.dataSrcPicker = new AgroDatePicker();
		this.paneDataSrc.getChildren().add(this.dataSrcPicker.getDatePicker());
	}

	/**
	 * effettua la richiesta della modifica del partecipante dopo aver inserito la
	 * nuova data di rilascio e il percorso al fle
	 * @param event
	 */
	@FXML protected void btnAggiornaClicked(MouseEvent event){
		this.hideErrors();
		this.parTO.setDataSRC(this.dataSrcPicker.getSelectedDate());
		this.parTO.setSrc(this.fileSrc.getAbsolutePath());
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.parTO, "modificaPartecipante", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);
	}

	/**
	 * carica il file aggiornato
	 * @param event
	 */
	@FXML protected void btnCaricaClicked(MouseEvent event){
		File file = this.fileChooser.showOpenDialog(this.getStage(this.viewName));
		if (file != null) {
			this.fileSrc = file;
			this.lblSrc.setText(this.fileSrc.getName());
		}
	}

	/**
	 * nasconde gli errori di valizazione
	 */
	private void hideErrors(){
		this.lblDataSrcError.setVisible(false);
		this.lblSrcError.setVisible(false);
	}

	/**
	 * mostra gli errori di valizazione
	 * @param errors
	 * @param lblError
	 * @param errorKey
	 */
	private void showErrors(ErrorTO errors, Label lblError, String errorKey){
		if(errors.hasError(this.getError(errorKey))){
			String nomeKey = this.getError(errorKey);
			lblError.setVisible(true);
			lblError.setText(errors.getError(nomeKey));
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
			if(res instanceof ErrorTO){
				ErrorTO errors = (ErrorTO)res;
				if(errors.hasError(this.getError("srcKey"))){
					this.showErrors(errors, this.lblSrcError, "srcKey");
				}

				if(errors.hasError(this.getError("dataSrcKey"))){
					this.showErrors(errors, this.lblDataSrcError, "dataSrcKey");
				}
			} else if(res instanceof PartecipanteTO){
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.res.getString("key179"));
				this.setVista("messageDialog", succMessage);
				this.close();
			}
		}
	}
}