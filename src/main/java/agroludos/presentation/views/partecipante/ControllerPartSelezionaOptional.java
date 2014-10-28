package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

public class ControllerPartSelezionaOptional extends AgroludosController implements Initializable{

	private String viewName;

	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;
	@FXML private Label lblTotaleComp;
	@FXML private Label lblTotaleOpt;
	@FXML private TableView<OptModel> tableOptScelti;
	@FXML private TableColumn<OptModel, String> nomeOsCol;
	@FXML private TableColumn<OptModel, String> prezzoOsCol;
	@FXML private TableColumn<OptModel, String> btnRemCol;
	@FXML private GridPane paneOptionalScelti;
	@FXML private GridPane paneTableOptional;
	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;

	private ObservableList<OptModel> optSceltiData;
	private TableOptional tableOptional;
	private Map<TipoOptionalTO, List<OptionalTO>> mainData;
	private Map<String, OptionalTO> optScelti;
	private Map<String, Integer> indScelti;
	private List<OptionalTO> optComp;
	private List<TipoOptionalTO> listTipiOpt;

	private CompetizioneTO mainComp;
	private IscrizioneTO mainIscr;

	private Integer nPassi;
	private int passoCorrente;
	private double totaleOpt;
	private Double totComp;

	private ResourceBundle res;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.mainIscr = (IscrizioneTO) mainTO;
			this.lblTotaleOpt.setText("0,00");
			this.totaleOpt = 0.0;			
			this.totComp = 0.0;
			this.optSceltiData = FXCollections.observableArrayList();
			this.nomeOsCol.setCellValueFactory(new PropertyValueFactory<OptModel, String>("nome"));
			this.prezzoOsCol.setCellValueFactory(new PropertyValueFactory<OptModel, String>("costo"));
			Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>> cellFactory =
					new Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>>() {
				@Override
				public TableCell<OptModel, String> call(TableColumn<OptModel, String> p) {
					return new DeleteTableCell();
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
			if(this.paneOptionalScelti.visibleProperty().getValue()){
				this.paneOptionalScelti.setVisible(false);
			}
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
						if(addOptScelto(optMod)){
							totaleOpt += opt.getCosto();
							totComp = totaleOpt + mainComp.getCosto();
						}
					}
				}

			});

			if( this.mainIscr.getAllOptionals().size() > 0 ){
				this.passoCorrente = this.nPassi - 1;
				this.btnAvanti.setVisible(false);
				this.tableOptional.setVisible(false);
				this.btnConferma.setVisible(true);
				for(OptionalTO opt : this.mainIscr.getAllOptionals()){
					OptModel o = new OptModel(opt);
					this.totaleOpt += o.getCosto();
					this.totComp = this.mainComp.getCosto() + this.totaleOpt;
					this.tableOptScelti.getItems().add(o);
					this.optScelti.put(opt.getTipoOptional().getNome(), opt);
				}
				showConfermaView();
				this.btnIndietro.setDisable(false);
			}

		}
	}

	@Override
	public String getViewName() {
		return this.viewName;
	}
	
	private class DeleteTableCell extends TableCell<OptModel, String> {

		private final Button btnElimina;

		public DeleteTableCell() {
			this.btnElimina = new Button("X");
			this.btnElimina.setStyle("-fx-base: red;");
			this.btnElimina.setAlignment(Pos.CENTER);

			setAlignment(Pos.CENTER);
			setGraphic(this.btnElimina);

			this.btnElimina.setOnAction(new EventHandler<ActionEvent> () {

				@Override
				public void handle(ActionEvent t) {
					int index = getIndex();

					OptModel optMod = tableOptScelti.getItems().get(index);
					OptionalTO optTO = optMod.getOptTO();
					String nomeTipo = optTO.getTipoOptional().getNome();
					indScelti.remove(nomeTipo);
					tableOptScelti.getItems().remove(index);
					optScelti.remove(nomeTipo);

					DecimalFormat df = new DecimalFormat("#.00");
					totaleOpt -= optTO.getCosto();
					if(optScelti.size() == 0){
						lblTotaleOpt.setText("0,00");
					} else {
						lblTotaleOpt.setText(df.format(totaleOpt));
					}

					totComp = totaleOpt + mainComp.getCosto();
					lblTotaleComp.setText(df.format(totComp));
				}

			});
		} 

		@Override 
		public void updateItem(String item, boolean empty) {
			super.updateItem(item, empty);
			if (empty) {
				setText(null);
				setGraphic(null);
			} else {
				setGraphic(this.btnElimina);
			}
		}
	}

	private OptModel getOptMod(OptModel optMod){
		OptModel res = null;
		ObservableList<OptModel> mainList = this.tableOptScelti.getItems();

		for(OptModel o : mainList){
			OptionalTO eOpt = o.getOptTO();
			OptionalTO nOpt = optMod.getOptTO();
			if(eOpt.getTipoOptional().equals(nOpt.getTipoOptional())){
				res = o;
				break;
			}
		}

		return res;
	}

	private int getOptModIndex(OptModel optMod){
		int index = -1;
		ObservableList<OptModel> mainList = this.tableOptScelti.getItems();
		OptModel opt = this.getOptMod(optMod);
		if(opt != null)
			index = mainList.indexOf(opt);
		return index;
	}

	private boolean isEqualMod(OptModel optMod){
		boolean res = false;
		OptModel testMod = this.getOptMod(optMod);
		if(testMod != null)
			res = optMod.getOptTO().equals(testMod);
		return res;
	}

	private boolean addOptScelto(OptModel optMod){
		boolean res = false;

		ObservableList<OptModel> mainList = this.tableOptScelti.getItems(); 

		if( mainList.size() == 0 ){
			mainList.add(optMod);
			res = true;
		} else if( !this.isEqualMod(optMod) ){
			int index = this.getOptModIndex(optMod);
			if( index >= 0 ) {
				this.totaleOpt -= mainList.get(index).getCosto();
				mainList.remove(index);
				mainList.add(index, optMod);
				res = true;
			} else {
				mainList.add(optMod);
				res = true;
			}
		}

		return res;
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
		if(this.passoCorrente != (this.nPassi - 1)){
			this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		} else {
			this.lblTipoOptional.setText(this.res.getString("key144"));
		}
		StringBuilder sb = new StringBuilder(10);
		sb.append(this.res.getString("key145"));
		sb.append(" ");
		sb.append(this.passoCorrente + 1);
		sb.append(" ");
		sb.append(this.res.getString("key146"));
		sb.append(" ");
		sb.append(this.nPassi);
		this.lblPassi.setText(sb.toString());
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

	private void showConfermaView(){
		this.paneOptionalScelti.setVisible(true);
		if(this.optScelti.size() == 0){
			this.tableOptScelti.setVisible(false);
		} else {
			this.tableOptScelti.setVisible(true);
			DecimalFormat df = new DecimalFormat("#.00");
			this.lblTotaleOpt.setText(df.format(this.totaleOpt));
			this.lblTotaleComp.setText(df.format(this.totComp));
		}
	}

	@FXML protected void btnAvanti(MouseEvent event) {
		this.passoCorrente++;
		this.setLabelDialog();

		if(this.passoCorrente == (this.nPassi - 1)){
			this.btnAvanti.setVisible(false);
			this.tableOptional.setVisible(false);
			this.btnConferma.setVisible(true);
			showConfermaView();
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
		for (OptionalTO opt : this.optScelti.values()) {
			this.mainIscr.addOptional(opt);
		}
		this.mainIscr.setCosto(this.totComp);
		this.close();

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.mainIscr, "modificaIscrizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}


	@Override
	public void forward(AgroRequest request, AgroResponse response) {

	}
}