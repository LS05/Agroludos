package agroludos.presentation.views.mds;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import agroludos.presentation.views.tablemodel.CmpModel;
import agroludos.presentation.views.tablemodel.MdcModel;
import agroludos.presentation.views.tablemodel.OptModel;
import agroludos.presentation.views.tablemodel.PartModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.PartecipanteTO;

public class ControllerMdsMain extends ControllerUtenti{

	//pane centrali
	@FXML private GridPane paneGestioneCompetizioni;
	@FXML private GridPane paneGestioneOptional;
	@FXML private GridPane paneGestioneManagerCompetizione;
	@FXML private GridPane paneGestionePartecipanti;

	//button mainView
	@FXML private Button btnGestManComp;
	@FXML private Button btnGestComp;
	@FXML private Button btnGestOptional;
	@FXML private Button btnGestPart;

	//button gest competizioni
	@FXML private TableView<CmpModel> tableCompetizioni;
	@FXML private TableColumn<OptModel, String> cmpColNome;
	@FXML private TableColumn<OptModel, String> cmpColDesc;
	@FXML private TableColumn<OptModel, String> cmpColPrezzo;
	@FXML private TableColumn<OptModel, String> cmpColStato;
	@FXML private Button btnCorsaCampestre;
	@FXML private Button btnTiroConArco;
	@FXML private Button btnNuovoTipoCompetizione;
	private List<CompetizioneTO> listComp;

	//gestione partecipanti
	@FXML private TableView<PartModel> tablePartecipanti;
	@FXML private TableColumn<PartModel, String> partColNome;
	@FXML private TableColumn<PartModel, String> partColCognome;
	@FXML private TableColumn<PartModel, String> partColEmail;
	@FXML private TableColumn<PartModel, String> partColUsername;

	@FXML private Label lblParNome;
	@FXML private Label lblParCognome;
	@FXML private Label lblParUsername;
	@FXML private Label lblParStato;
	@FXML private Label lblParDataSRC;
	@FXML private Label lblParCodFisc;
	@FXML private Label lblParIndirizzo;
	@FXML private Label lblParSesso;
	@FXML private Label lblParEmail;
	@FXML private Label lblParAnnoNasc;
	@FXML private Label lblParNumTessSan;

	//gestione Optionlal
	@FXML private Button btnPranzo;
	@FXML private Button btnMerenda;
	@FXML private Button btnPernotto;
	@FXML private Button btnNuovoTipoOptional;
	@FXML private Button btnDisattivaOptional;

	@FXML private TableView<OptModel> tableOptional;
	@FXML private TableColumn<OptModel, String> optColNome;
	@FXML private TableColumn<OptModel, String> optColDesc;
	@FXML private TableColumn<OptModel, String> optColPrezzo;
	@FXML private TableColumn<OptModel, String> optColStato;
	private List<OptionalTO> listOpt;

	//gestione manager di competizione
	@FXML private TableView<MdcModel> tableManagerCompetizione;
	@FXML private TableColumn<MdcModel, String> mdcNomeCol;
	@FXML private TableColumn<MdcModel, String> mdcCognomeCol;
	@FXML private TableColumn<MdcModel, String> mdcEmailCol;

	private List<ManagerDiCompetizioneTO> listMdc;
	private ObservableList<MdcModel> listaTabMdc;
	private int selectedMDC;

	@FXML private Label lblMdcNome;
	@FXML private Label lblMdcCognome;
	@FXML private Label lblMdcUsername;
	@FXML private Label lblMdcStato;
	@FXML private Label lblMdcEmail;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	private List<String> richieste;
	private List<PartecipanteTO> listPart;
	private int selectedPart;

	@SuppressWarnings("serial")
	@Override
	public void initializeView() {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);

		this.richieste = new ArrayList<String>(){{
			this.add("getAllManagerDiCompetizione");
			this.add("getAllOptional");
			this.add("getAllPartecipante");
			this.add("getAllCompetizione");
		}};

		initRequests();

		this.listaTabMdc = this.getListTabellaMdC();
		this.selectedMDC = 0;
		this.selectedPart = 0;
		this.initMdcTable();
		this.initOptTable();
		this.initCompTable();
		this.initPartTable();
	}

	private void initRequests(){
		for(String req : this.richieste){
			this.richiesta = this.getRichiesta(req);
			this.risposta = respFact.createResponse();
			frontController.eseguiRichiesta(this.richiesta, this.risposta);
		}
	}

	//----------------Main View--------------------

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestOptional(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(true);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestManComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(true);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestPart(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(true);
	}

	@FXML protected void btnDisattivaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		OptionalTO optTO = optModel.getOptTO();
		AgroRequest request = this.getRichiesta(optTO, "disattivaOptional");
		AgroResponse response = respFact.createResponse();
		frontController.eseguiRichiesta(request, response);
	}

	//--------------------Gest Man Competizione ---------------

	@FXML protected void modificaManagerCompetizione(MouseEvent event){

		MdcModel mdcMod = this.tableManagerCompetizione.getSelectionModel().getSelectedItem();
		ManagerDiCompetizioneTO mdcto = this.getManagerDiCompetizione(mdcMod.getUsername());
		this.selectedMDC = this.tableManagerCompetizione.getSelectionModel().getSelectedIndex();
		nav.setVista("modificaMDC", mdcto);
	}

	//--------------------Gest Competizioni View---------------

	@FXML protected void btnCorsaCampestre(MouseEvent event) {
		//caricare competizioni di corsa campestre nella tabella
	}

	@FXML protected void btnTiroConArco(MouseEvent event) {
		//caricare competizioni di tiro con l'arco nella tabella
	}

	@FXML protected void btnNuovoTipoCompetizione(MouseEvent event) {
	}

	//--------------------Gest Optional View---------------

	@FXML protected void btnPranzo(MouseEvent event) {
		//caricare optional nella tabella
	}

	@FXML protected void btnMerenda(MouseEvent event) {
		//caricare optional nella tabella
	}

	@FXML protected void btnPernotto(MouseEvent event) {
		//caricare optional nella tabella
	}

	@FXML protected void btnNuovoTipoOptional(MouseEvent event) {
	}

	private ObservableList<MdcModel> getListTabellaMdC(){
		ObservableList<MdcModel> res = FXCollections.observableArrayList();
		MdcModel modelMdc = null;

		res.addListener(new ListChangeListener<MdcModel>() {

			@Override
			public void onChanged(Change<? extends MdcModel> c) {
				while (c.next()) {
					if (c.wasUpdated()) {
						//update MdcModel
						System.out.println("Elemento della lista cambiato!");
					} 
				}

			}
		});

		for(ManagerDiCompetizioneTO mdc : this.listMdc){
			modelMdc = new MdcModel(mdc);
			res.add(modelMdc);
		}

		return res;
	}

	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}

	private void setDxColumn(Integer selected){
		MdcModel selModel = tableManagerCompetizione.getItems().get(selected);
		this.lblMdcNome.setText(selModel.getNome());
		this.lblMdcCognome.setText(selModel.getCognome());
		this.lblMdcEmail.setText(selModel.getEmail());
		this.lblMdcUsername.setText(selModel.getUsername());
		this.lblMdcStato.setText(selModel.getStato());
	}

	private void initMdcTable(){
		this.initColumn(this.mdcNomeCol, "nome");
		this.initColumn(this.mdcCognomeCol, "cognome");
		this.initColumn(this.mdcEmailCol, "email");

		this.tableManagerCompetizione.getItems().setAll(this.listaTabMdc);

		this.tableManagerCompetizione.getSelectionModel().select(0);

		this.setDxColumn(this.selectedMDC);

		this.tableManagerCompetizione.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<MdcModel>(){

					@Override
					public void changed(ObservableValue<? extends MdcModel> mdcModel,
							MdcModel oldMod, MdcModel newMod) {

						selectedMDC = tableManagerCompetizione.getSelectionModel().getSelectedIndex();
						setDxColumn(selectedMDC);
					}

				});
	}

	private ManagerDiCompetizioneTO getManagerDiCompetizione(String username){
		ManagerDiCompetizioneTO res = null;

		for(ManagerDiCompetizioneTO m : this.listMdc){
			if(m.getUsername().equals(username)){
				res = m;
				break;
			}
		}

		return res;
	}

	private void initOptTable(){
		this.initColumn(this.optColNome, "nome");
		this.initColumn(this.optColDesc, "descrizione");
		this.initColumn(this.optColPrezzo, "costo");
		this.initColumn(this.optColStato, "nomeStato");

		this.optColStato.setCellFactory(new Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>>() {
			@Override public TableCell<OptModel, String> call(TableColumn<OptModel, String> statoColumn) {
				return new TableCell<OptModel, String>() {
					@Override public void updateItem(final String item, final boolean empty) {
						super.updateItem(item, empty);

						// clear any custom styles

						this.getStyleClass().remove("disattivoCell");
						this.getStyleClass().remove("attivoCell");
						this.getTableRow().getStyleClass().remove("disattivoRow");
						this.getTableRow().getStyleClass().remove("attivoRow");

						// update the item and set a custom style if necessary
						if (item != null) {
							setText(item.toString());
							int index = this.getIndex();
							OptModel optional = this.getTableView().getItems().get(index);
							this.getStyleClass().add(optional.getStato() == 0 ? "disattivoCell" : "attivoCell");
							this.getTableRow().getStyleClass().add(optional.getStato() == 0 ? "disattivoRow" : "attivoRow");
						}
					}
				};
			}
		});

		//		this.optColIdStato.setVisible(false);

		ObservableList<OptModel> res = FXCollections.observableArrayList();

		for(OptionalTO opt : this.listOpt){
			OptModel modelOpt = new OptModel(opt);
			res.add(modelOpt);
		}

		this.tableOptional.getItems().setAll(res);
	}

	private void initCompTable(){
		this.initColumn(this.cmpColNome, "nome");
		this.initColumn(this.cmpColDesc, "descrizione");
		this.initColumn(this.cmpColPrezzo, "costo");
		this.initColumn(this.cmpColStato, "stato");

		ObservableList<CmpModel> res = FXCollections.observableArrayList();

		for(CompetizioneTO comp : this.listComp){
			CmpModel modelCmp = new CmpModel(comp);
			res.add(modelCmp);
		}

		this.tableCompetizioni.getItems().setAll(res);
	}

	private void setDxPartColumn(int selected) {
		PartModel selModel = this.tablePartecipanti.getItems().get(selected);
		this.lblParNome.setText(selModel.getNome());
		this.lblParCognome.setText(selModel.getCognome());
		this.lblParEmail.setText(selModel.getEmail());
		this.lblParUsername.setText(selModel.getUsername());
		//		this.lblParStato.setText(selModel.getStato());
		this.lblParDataSRC.setText(selModel.getDataSRC());
		this.lblParCodFisc.setText(selModel.getCf());
		this.lblParIndirizzo.setText(selModel.getIndirizzo());
		this.lblParSesso.setText(selModel.getSesso());
		this.lblParAnnoNasc.setText(selModel.getDataNasc());
		this.lblParNumTessSan.setText(selModel.getNumTessera());
	}

	private void initPartTable(){
		this.initColumn(this.partColNome, "nome");
		this.initColumn(this.partColCognome, "cognome");
		this.initColumn(this.partColEmail, "email");
		this.initColumn(this.partColUsername, "username");

		ObservableList<PartModel> res = FXCollections.observableArrayList();

		for(PartecipanteTO part : this.listPart){
			PartModel partModel = new PartModel(part);
			res.add(partModel);
		}

		this.tablePartecipanti.getItems().setAll(res);

		this.tablePartecipanti.getSelectionModel().select(0);

		this.setDxPartColumn(this.selectedPart);

		this.tablePartecipanti.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<PartModel>(){

					@Override
					public void changed(ObservableValue<? extends PartModel> partModel,
							PartModel oldMod, PartModel newMod) {

						selectedPart = tablePartecipanti.getSelectionModel().getSelectedIndex();
						setDxPartColumn(selectedPart);
					}

				});
	}	

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.reqProperties.getProperty("getAllManagerDiCompetizione") )){

			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<ManagerDiCompetizioneTO> mdcList = (List<ManagerDiCompetizioneTO>)res;
				this.listMdc = mdcList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("getAllOptional") )){

			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<OptionalTO> optList = (List<OptionalTO>)res;
				this.listOpt = optList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("getAllPartecipante") )){

			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<PartecipanteTO> mdcList = (List<PartecipanteTO>)res;
				this.listPart = mdcList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("getAllCompetizione") )){

			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<CompetizioneTO> comList = (List<CompetizioneTO>)res;
				this.listComp = comList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("modificaManagerDiCompetizione") )){

			Object res = (Object)response.getRespData();
			if(res instanceof ManagerDiCompetizioneTO){
				ManagerDiCompetizioneTO mdcTO = (ManagerDiCompetizioneTO)res;
				MdcModel mdc = this.tableManagerCompetizione.getItems().get(this.selectedMDC);
				mdc.setNome(mdcTO.getNome());
				mdc.setCognome(mdcTO.getCognome());
				mdc.setEmail(mdcTO.getEmail());
				mdc.setUsername(mdcTO.getUsername());
				mdc.setStato(mdcTO.getStatoUtente().getNome());
				setDxColumn(this.selectedMDC);
			}

		}
	}
}