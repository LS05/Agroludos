package agroludos.presentation.views.mds;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.IscrizioneTO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdsIscrizione extends AgroludosController {
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
	@FXML private Label lblCostoIsc;
	@FXML private Button btnVisualizzaCertificato;
	@FXML private GridPane paneIscrizione;
	@FXML private GridPane paneTableOptionalScelti;

	private TableOptional tableOptional;
	private IscrizioneTO iscto;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.iscto = (IscrizioneTO)mainTO;

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
			this.lblCostoIsc.setText(String.valueOf(this.iscto.getCosto()));

			this.tableOptional = new TableOptional();
			this.paneTableOptionalScelti.getChildren().add(this.tableOptional);
			this.paneTableOptionalScelti.setVisible(true);
			this.tableOptional.setAll(this.iscto.getAllOptionals());

			this.tableOptional.hideColumn("Stato");
			this.tableOptional.hideColumn("Descrizione");
		}
	}

	@FXML private void btnVisualizzaCertificato(MouseEvent event){
		this.setVista("visualizzaSRC",this.iscto.getPartecipante());
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
	}
}
