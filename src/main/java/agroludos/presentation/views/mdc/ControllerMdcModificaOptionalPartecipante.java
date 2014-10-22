package agroludos.presentation.views.mdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.presentation.views.partecipante.DeleteTableCell;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdcModificaOptionalPartecipante extends AgroludosController{

	private String viewName;

	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;

	@FXML private TableView<OptModel> tableOptScelti;
	@FXML private TableColumn<OptModel, String> nomeOsCol;
	@FXML private TableColumn<OptModel, String> prezzoOsCol;
	@FXML private TableColumn<OptModel, String> btnRemCol;
	private ObservableList<OptModel> optSceltiData;

	@FXML private Label lblCostoOptScelti;
	@FXML private GridPane paneOptionalScelti;
	private TableOptional tableOptional;
	@FXML private GridPane paneTableOptional;
	private Map<TipoOptionalTO, List<OptionalTO>> mainData;

	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;
	@FXML private ListView<String> listViewOptional;

	private CompetizioneTO mainComp;
	private List<OptionalTO> optComp;
	private List<OptionalTO> optIsc;

	private IscrizioneTO mainIscr;

	private List<TipoOptionalTO> listTipiOpt;

	private Map<String, OptionalTO> optScelti;
	private Map<String, Integer> indScelti;

	private Integer nPassi;
	private int passoCorrente;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){

			this.mainIscr = (IscrizioneTO) mainTO;

			this.optSceltiData = FXCollections.observableArrayList();


			this.nomeOsCol.setCellValueFactory(new PropertyValueFactory<OptModel, String>("nome"));

			this.prezzoOsCol.setCellValueFactory(new PropertyValueFactory<OptModel, String>("costo"));

			Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>> cellFactory =
					new Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>>() {
				@Override
				public TableCell<OptModel, String> call(TableColumn<OptModel, String> p) {
					return new DeleteTableCell(indScelti, tableOptScelti);
				}
			};

			this.btnRemCol.setCellValueFactory(new PropertyValueFactory<OptModel, String>("nome"));

			this.btnRemCol.setCellFactory(cellFactory);

			this.tableOptScelti.getItems().setAll(this.optSceltiData);

			this.mainComp = this.mainIscr.getCompetizione();

			this.optComp = this.mainComp.getAllOptionals();

			this.mainData = new HashMap<TipoOptionalTO, List<OptionalTO>>();

			this.optScelti = new HashMap<String, OptionalTO>();
			this.indScelti = new HashMap<String, Integer>();

			this.passoCorrente = 0;

			this.btnAvanti.setVisible(true);
			this.btnConferma.setVisible(false);
			this.btnIndietro.setVisible(true);
			this.btnIndietro.setDisable(true);


			this.initData();

			this.setLabelDialog();

			this.tableOptional = new TableOptional();
			this.tableOptional.hideColumn(3);
			this.paneTableOptional.getChildren().add(this.tableOptional);
			this.paneTableOptional.setVisible(true);		

			this.setTable((TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente));

			this.tableOptional.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					TipoOptionalTO tipoCorr = (TipoOptionalTO)listTipiOpt.get(passoCorrente);
					OptModel optMod = tableOptional.getSelectedItem();
					if(optMod != null){
						OptionalTO opt = optMod.getOptTO();
						optScelti.put(tipoCorr.getNome(), opt);
						indScelti.put(tipoCorr.getNome(), tableOptional.getSelectedIndex());
						addOptScelto(optMod);
					}
				}

			});
		}
		if(!this.mainIscr.getAllOptionals().isEmpty()){
			this.optIsc = this.mainIscr.getAllOptionals();
			for(OptionalTO opt: this.optIsc){
				OptModel optMod = new OptModel(opt);
				this.optSceltiData.add(optMod);
			}
			this.tableOptScelti.getItems().clear();
			this.tableOptScelti.getItems().setAll(this.optSceltiData);
			this.passoCorrente = this.nPassi-1;
			this.setLabelDialog();
			this.btnAvanti.setVisible(false);
			this.btnIndietro.setDisable(false);
			this.tableOptional.setVisible(false);
			this.btnConferma.setVisible(true);
			this.paneOptionalScelti.setVisible(true);

		}else{
			this.tableOptional.setVisible(true);
			this.paneOptionalScelti.setVisible(false);
		}
	}
	private int getOptModIndex(OptModel optMod){
		int index = -1;
		ObservableList<OptModel> mainList = this.tableOptScelti.getItems();

		for(OptModel o : mainList){
			OptionalTO eOpt = o.getOptTO();
			OptionalTO nOpt = optMod.getOptTO();
			if(eOpt.getTipoOptional().equals(nOpt.getTipoOptional())){
				index = mainList.indexOf(o);
				break;
			}
		}

		return index;
	}

	private void addOptScelto(OptModel optMod){
		int index = this.getOptModIndex(optMod);

		ObservableList<OptModel> mainList = this.tableOptScelti.getItems(); 
		if( mainList.size() == 0 ){
			mainList.add(optMod);
		} else if( index >= 0 ) {
			mainList.remove(index);
			mainList.add(index, optMod);
		} else {
			mainList.add(optMod);
		}
	}

	private void initData(){
		this.listTipiOpt = new ArrayList<TipoOptionalTO>();

		for(OptionalTO optional : this.optComp){
			TipoOptionalTO tipo = optional.getTipoOptional();
			if(!this.mainData.containsKey(tipo)){
				this.listTipiOpt.add(tipo);
				ArrayList<OptionalTO> nList = new ArrayList<OptionalTO>();
				nList.add(optional);
				this.mainData.put(tipo, nList);
			} else {
				List<OptionalTO> eList = this.mainData.get(tipo);
				eList.add(optional);
				this.mainData.put(tipo, eList);
			}
		}

		int steps = this.mainData.keySet().size();

		this.nPassi = steps + 1;

		if(steps == 1){
			this.btnAvanti.setVisible(false);
			this.btnConferma.setVisible(true);
			this.btnIndietro.setDisable(true);
		}
	}

	private void setLabelDialog(){
		if(this.passoCorrente != (this.nPassi - 1))
			this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		this.lblPassi.setText("Passo " + (this.passoCorrente + 1) + " di " + this.nPassi);
	}

	private void setTable(TipoOptionalTO tipoOpt){
		List<OptionalTO> tblList = new ArrayList<OptionalTO>();

		for(OptionalTO opt : this.optComp){
			if(opt.getTipoOptional().equals(tipoOpt)){
				tblList.add(opt);
			}
		}

		this.tableOptional.setAll(tblList);
	}

	@FXML protected void btnIndietro(MouseEvent event) { 
		this.passoCorrente--;

		if(this.passoCorrente == 0)
			this.btnIndietro.setDisable(true);

		if(!this.tableOptional.visibleProperty().getValue()){
			this.tableOptional.setVisible(true);
		}

		this.setLabelDialog();
		this.paneOptionalScelti.setVisible(false);
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);

		TipoOptionalTO tipoCorr = (TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente);
		this.setTable(tipoCorr);

		if(this.indScelti.containsKey(tipoCorr.getNome())){
			this.tableOptional.getSelectionModel().select(this.indScelti.get(tipoCorr.getNome()));
		}
	}


	@FXML protected void btnAvanti(MouseEvent event) {
		this.passoCorrente++;
		this.setLabelDialog();

		if(this.passoCorrente == (this.nPassi - 1)){
			this.btnAvanti.setVisible(false);
			this.tableOptional.setVisible(false);
			this.btnConferma.setVisible(true);
			this.paneOptionalScelti.setVisible(true);
		} else {
			this.btnIndietro.setDisable(false);
			this.setTable((TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente));
			TipoOptionalTO tipoCorr = (TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente);
			this.setTable(tipoCorr);

			if(this.indScelti.containsKey(tipoCorr.getNome())){
				this.tableOptional.getSelectionModel().select(this.indScelti.get(tipoCorr.getNome()));
			}
		}

	}

	@FXML protected void btnConferma(MouseEvent event) {
		this.mainIscr.clearOptionals();

		for (OptModel optMod : this.tableOptScelti.getItems()) {
			this.mainIscr.addOptional(optMod.getOptTO());
		}
		this.risposta = respFact.createResponse();
			this.richiesta = this.getRichiesta(this.mainIscr, "modificaIscrizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.close();
	}

	@Override
	public String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}