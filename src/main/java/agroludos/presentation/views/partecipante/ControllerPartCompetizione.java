package agroludos.presentation.views.partecipante;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.QuestionTO;

public class ControllerPartCompetizione extends AgroludosController {

	private String viewName;

	private CompetizioneTO cmpto;

	@FXML private GridPane paneVisualizzaCmp;
	@FXML private GridPane paneIscritti;
	@FXML private GridPane paneTableOptional;
	private TableOptional tableOptional;
	private List<OptionalTO> listOpt;

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
	//mostra messaggio di modifica avvenuta
	@FXML private Label lblModificaOk;
	//mostra il messaggio di annullamento avvenuto
	@FXML private Label lblAnnullaOk;

	//bottoni annulla e modifica competizione
	@FXML private Button btnAnnullaCmp;
	@FXML private Button btnModificaCmp;

	//bottoni per visualizzare e annullare un'iscrizione
	@FXML private Button btnAnnullaIsc;
	@FXML private Button btnVisualizzaIsc;
	@FXML private Label lblEliminaIsc;

	private Stage stage;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		//inizializzazione interfaccia
		this.paneVisualizzaCmp.setVisible(true);
		this.paneVisualizzaCmp.setDisable(false);
		this.paneIscritti.setDisable(false);
		this.lblModificaOk.setVisible(false);
		this.lblAnnullaOk.setVisible(false);
		this.btnAnnullaIsc.setDisable(true);
		this.lblEliminaIsc.setVisible(false);

		this.cmpto = toFact.createCompetizioneTO();
		this.cmpto =(CompetizioneTO) mainTO;

		this.lblNomeCompetizione.setText(this.cmpto.getNome());
		this.lblData.setText(this.cmpto.getData().toString());
		this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
		this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
		this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.lblTipo.setText(this.cmpto.getTipoCompetizione().getNome());
		this.lblNiscritti.setText(Integer.toString(this.cmpto.getAllIscrizioniAttive().size()));
		this.lblStato.setText(this.cmpto.getStatoCompetizione().getNome());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());

		//popolo la lista delle iscrizioni
		this.listIsc = this.cmpto.getAllIscrizioniAttive();

		this.listaTabIsc = this.getListTabellaIsc();
		this.initIscTable();

		this.tableOptional = new TableOptional();
		this.paneTableOptional.getChildren().add(this.tableOptional);
		this.paneTableOptional.setVisible(true);
		this.tableOptional.setAll(this.cmpto.getAllOptionals());

		this.tableOptional.hideColumn("Stato");
		this.tableOptional.hideColumn("Descrizione");

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
						nav.setVista("mostraIscrizione", iscModelRow.getIscrizioneTO());
					else
						btnAnnullaIsc.setDisable(false);
				}
			}

		});
	}

	@FXML protected void btnAnnullaIsc(MouseEvent event) {

		IscrizioneTO iscto = this.tblIscritti.getSelectionModel().getSelectedItem().getIscrizioneTO();
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion("Vuoi eliminare l'iscrizione selezionata?");

		question.setDataTO(iscto);
		question.setRequest("eliminaIscrizione");
		question.setViewName(this.viewName);

		nav.setVista("questionDialog", question);



	}

	@FXML protected void btnAnnullaCmp(MouseEvent event) {

		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion("Vuoi eliminare la competizione?");

		question.setDataTO(this.cmpto);
		question.setRequest("annullaCompetizione");
		question.setViewName(this.viewName);

		nav.setVista("questionDialog", question);

	}

	@FXML protected void btnModificaCmp(MouseEvent event) {

		this.lblModificaOk.setVisible(false);
		this.lblAnnullaOk.setVisible(false);
		nav.setVista("mostraModificaCmp", this.cmpto);
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
		if(request.getCommandName().equals("modificaCompetizione")){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				this.initializeView((CompetizioneTO) res);
				this.lblModificaOk.setVisible(true);
			}
		}
	}
}