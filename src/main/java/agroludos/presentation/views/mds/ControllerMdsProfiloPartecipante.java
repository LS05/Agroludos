package agroludos.presentation.views.mds;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableIscrizioni;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.AgroludosTO;
import agroludos.to.PartecipanteTO;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdsProfiloPartecipante extends AgroludosController{
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

	private PartecipanteTO parTO;

	@FXML private GridPane paneIscrizione;
	private TableIscrizioni tableIscrizioni;

	protected IscModel iscModelRow;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof PartecipanteTO){
			this.parTO =(PartecipanteTO) mainTO;



			this.lblNomeIsc.setText(this.parTO.getNome());
			this.lblCognomeIsc.setText(this.parTO.getCognome());
			this.lblEmailIsc.setText(this.parTO.getEmail());
			this.lblCFIsc.setText(this.parTO.getCf());
			this.lblDataNascitaIsc.setText(this.parTO.getDataNasc().toString());
			this.lblSessoIsc.setText(this.parTO.getSesso());
			this.lblNTSIsc.setText(this.parTO.getNumTS());
			this.lblIndirizzoIsc.setText(this.parTO.getIndirizzo());
			this.lblDataSRCIsc.setText(this.parTO.getDataSRC().toString());

			this.tableIscrizioni = new TableIscrizioni();
			this.paneIscrizione.getChildren().add(this.tableIscrizioni);
			this.paneIscrizione.setVisible(true);
			this.tableIscrizioni.setAll(this.parTO.getAllIscrizioni());
			
			this.tableIscrizioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@SuppressWarnings("unchecked")
				@Override
				public void handle(MouseEvent event) {
					if (event.getClickCount() > 1) {
						TableView<IscModel> table = (TableView<IscModel>) event.getSource();
						iscModelRow = table.getSelectionModel().getSelectedItem();
						if(iscModelRow != null)
							setVista("mostraIscrizioneMds", iscModelRow.getIscrizioneTO());
					}
				}
			});

		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@FXML private void btnVisualizzaCertificato(MouseEvent event){
		this.setVista("visualizzaSRC",this.parTO);
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
	}
}

