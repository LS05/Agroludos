package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;

public class ControllerMdsCompetizione extends AgroludosController{

	private String viewName;

	private CompetizioneTO cmpto;

	@FXML private GridPane paneVisualizzaCmp;
	@FXML private GridPane paneIscritti;
	@FXML private GridPane paneTableOptional;
	private TableOptional tableOptional;

	//tabella iscrizioni alla competizione
	@FXML private TableView<IscModel> tblIscritti;
	@FXML private TableColumn<IscModel, String> iscNomeCol;
	@FXML private TableColumn<IscModel, String> iscCognomeCol;
	@FXML private TableColumn<IscModel, String> iscEmailCol;
	private ObservableList<IscModel> listaTabIsc;
	private List<IscrizioneTO> listIsc;
	private IscModel iscModelRow;

	//label della competizione da visualizzare
	@FXML private Label lblNomeCompetizione;
	@FXML private Label lblData;
	@FXML private Label lblNmin;
	@FXML private Label lblNmax;
	@FXML private Label lblCosto;
	@FXML private Label lblTipo;
	@FXML private Label lblNiscritti;
	@FXML private Label lblStato;
	@FXML private TextArea txtDescrizione;

	private AgroRequest richiesta;

	private AgroResponse risposta;


	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof CompetizioneTO){
			//inizializzazione interfaccia
			this.paneVisualizzaCmp.setVisible(true);
			this.paneVisualizzaCmp.setDisable(false);
			this.paneIscritti.setDisable(false);

			this.cmpto = toFact.createCompetizioneTO();
			this.cmpto =(CompetizioneTO) mainTO;

			this.lblNomeCompetizione.setText(this.cmpto.getNome());
			this.lblData.setText(this.cmpto.getData().toString());
			this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
			this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
			this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
			this.lblTipo.setText(this.cmpto.getTipoCompetizione().getNome());

			//richiesta per ottenere le iscrizioni attive di questa competizione
			this.richiesta = this.getRichiesta(this.cmpto, "getAllIscrizioniAttiveByCmp", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

			this.lblNiscritti.setText(Integer.toString(this.listIsc.size()));
			this.lblStato.setText(this.cmpto.getStatoCompetizione().getNome());
			this.txtDescrizione.setText(this.cmpto.getDescrizione());

			this.listaTabIsc = this.getListTabellaIsc();
			this.initIscTable();

			this.tableOptional = new TableOptional();
			this.paneTableOptional.getChildren().add(this.tableOptional);
			this.paneTableOptional.setVisible(true);
			this.tableOptional.setAll(this.cmpto.getAllOptionals());

			this.tableOptional.hideColumn(2);
		}

	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;

	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}


	private ObservableList<IscModel> getListTabellaIsc(){
		ObservableList<IscModel> res = FXCollections.observableArrayList();
		IscModel modelIsc = null;

		for(IscrizioneTO isc : this.listIsc){
			modelIsc = new IscModel(isc);
			res.add(modelIsc);
		}

		return res;
	}

	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}

	private void initIscTable(){
		this.initColumn(this.iscNomeCol, "nome");
		this.initColumn(this.iscCognomeCol, "cognome");
		this.initColumn(this.iscEmailCol, "email");

		this.tblIscritti.getItems().setAll(this.listaTabIsc);

		this.tblIscritti.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				@SuppressWarnings("unchecked")
				TableView<IscModel> table = (TableView<IscModel>) event.getSource();
				iscModelRow = table.getSelectionModel().getSelectedItem();
				if(iscModelRow != null){
					if (event.getClickCount() > 1) 
						setVista("mostraIscrizioneMds", iscModelRow.getIscrizioneTO());
				}
			}

		});
	}


	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("getAllIscrizioniAttiveByCmp") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				//popolo la lista delle iscrizioni
				this.listIsc = (List<IscrizioneTO>) res;
			}
		}
	}
}