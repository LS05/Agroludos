package agroludos.presentation.views.mdc;

import java.util.ArrayList;
import java.util.List;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.QuestionTO;
import agroludos.to.SuccessTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcIscrizione extends AgroludosController {
	private String viewName;

	@FXML private Label lblNomeIsc;
	@FXML private Label lblCognomeIsc;
	@FXML private Label lblEmailIsc;
	@FXML private Label lblCFIsc;
	@FXML private Label lblDataNascitaIsc;
	@FXML private Label lblSessoIsc;
	@FXML private Label lblNTSIsc;
	@FXML private Label lblIndirizzoIsc;
	@FXML private Label lblDataSRCIsc;
	@FXML private Label lblDataIsc;


	@FXML private Button btnVisualizzaCertificato;
	@FXML private Button btnAnnullaIscrizione;
	@FXML private Button btnModificaOptionalIscrizione;


	@FXML private Label lblEliminaIscrizioneOk;
	@FXML private GridPane paneIscrizione;
	private IscrizioneTO iscto;

	@FXML private GridPane paneTableOptionalScelti;
	private TableOptional tableOptional;

	private AgroResponse risposta;
	private AgroRequest richiesta;

	@FXML private void btnVisualizzaCertificato(){
		nav.setVista("visualizzaSRC",this.iscto.getPartecipante());
	}

	@FXML protected void btnAnnullaIscrizione(){
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion("Vuoi eliminare l'iscrizione selezionata?");

		question.setDataTO(this.iscto);
		question.setRequest("eliminaIscrizione");
		question.setViewName(this.viewName);

		nav.setVista("questionDialog", question);

	}

	@FXML protected void btnModificaOptionalIscrizione(MouseEvent event) {
		//TODO
		System.out.println("da fare");
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.iscto =(IscrizioneTO) mainTO;



		this.lblNomeIsc.setText(this.iscto.getPartecipante().getNome());
		this.lblCognomeIsc.setText(this.iscto.getPartecipante().getCognome());
		this.lblEmailIsc.setText(this.iscto.getPartecipante().getEmail());
		this.lblCFIsc.setText(this.iscto.getPartecipante().getCf());
		this.lblDataNascitaIsc.setText(this.iscto.getPartecipante().getDataNasc().toString());
		this.lblSessoIsc.setText(this.iscto.getPartecipante().getSesso());
		this.lblNTSIsc.setText(this.iscto.getPartecipante().getNumTS());
		this.lblIndirizzoIsc.setText(this.iscto.getPartecipante().getIndirizzo());
		this.lblDataSRCIsc.setText(this.iscto.getPartecipante().getDataSRC().toString());
		this.lblDataIsc.setText(this.iscto.getData().toString());

		this.tableOptional = new TableOptional();
		this.paneTableOptionalScelti.getChildren().add(this.tableOptional);
		this.paneTableOptionalScelti.setVisible(true);
		this.tableOptional.setAll(this.iscto.getAllOptionals());

		this.tableOptional.hideColumn("Stato");
		this.tableOptional.hideColumn("Descrizione");


	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
	}
}
