package agroludos.presentation.views.mds;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.components.table.TableCompetizioni;
import agroludos.presentation.views.components.table.TableMdC;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.table.TablePartecipanti;
import agroludos.presentation.views.components.table.filter.TableCompetizioniFilter;
import agroludos.presentation.views.components.table.filter.TableMdcFilter;
import agroludos.presentation.views.components.table.filter.TableOptionalFilter;
import agroludos.presentation.views.components.table.filter.TablePartecipantiFilter;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.components.tablemodel.MdcModel;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.presentation.views.components.tablemodel.PartModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.QuestionTO;
import agroludos.to.SuccessMessageTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoCompetizioneTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsMain extends ControllerUtenti implements Initializable{
	//pane centrali
	@FXML private GridPane paneGestioneCompetizioni;
	@FXML private GridPane paneGestioneOptional;
	@FXML private GridPane paneGestioneMdC;
	@FXML private GridPane paneGestionePartecipanti;

	// gestione manager di competizione
	@FXML private GridPane paneTableMdC;
	@FXML private TableMdC tableManagerCompetizione;
	@FXML private Label lblMdcNome;
	@FXML private Label lblMdcCognome;
	@FXML private Label lblMdcUsername;
	@FXML private Label lblMdcStato;
	@FXML private Label lblMdcEmail;
	@FXML private Button btnResetCercaMdc;
	@FXML private TextField txtFilterMdc;
	private TableMdcFilter filterMdc;
	private List<ManagerDiCompetizioneTO> listMdc;
	private int selectedMdC = 0;

	//button gest competizioni
	@FXML private GridPane paneTableCmp;
	@FXML private TableCompetizioni tableCompetizioni;
	@FXML private TextField txtFilterComp;
	@FXML private GridPane paneListaTipiComp;
	@FXML private Button btnResetRicComp;
	private List<CompetizioneTO> listComp;
	private List<TipiAgroludosTO> listTipiComp;
	private TableCompetizioniFilter filterCmp;

	//gestione partecipanti
	@FXML private GridPane paneTablePart;
	@FXML private TablePartecipanti tablePartecipanti;
	@FXML private TextField txtCercaPart;
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
	@FXML private Button btnCercaCompetizioni;
	@FXML private TextField txtFilterPart;
	@FXML private Button btnResetRicPart;
	private List<PartecipanteTO> listPart;
	private int selectedPart = 0;
	private TablePartecipantiFilter filterPart;

	//gestione Optional
	@FXML private GridPane paneListaTipiOpt;
	@FXML private GridPane paneTableOptional;
	@FXML private TextField txtFilterOpt;
	@FXML private Button btnResetRicOpt;
	private TableOptional tableOptional;
	private TableOptionalFilter filterOpt;
	private List<TipiAgroludosTO> listTipiOpt;
	private List<OptionalTO> listOpt;

	private ListaViewTipi listViewOpt;

	private ListaViewTipi listViewComp;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	private CmpModel cmpModelRow;

	private ResourceBundle resources;
	protected PartModel partModelRow;

	private boolean checkMdc;
	private boolean checkOpt;

	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.resources = res;
	}

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;

		final Stage stage = this.getStage(this.viewName);
		//aggiungo l'evento close vista quando si chiude lo stage
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				chiusura();
				we.consume();
			}
		});

		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);

		this.richiesta = this.getRichiesta("getAllTipoCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.tableCompetizioni = new TableCompetizioni();
		this.tableCompetizioni.hideColumn(4);
		this.paneTableCmp.getChildren().add(this.tableCompetizioni);
		this.listViewComp = new ListaViewTipi(this.listTipiComp);
		this.paneListaTipiComp.getChildren().add(this.listViewComp);

		this.tableCompetizioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("unchecked")
					TableView<CmpModel> table = (TableView<CmpModel>) event.getSource();
					cmpModelRow = table.getSelectionModel().getSelectedItem();
					if(cmpModelRow != null)
						setVista("mostraCmpMds", cmpModelRow.getCompetizioneTO());
				}
			}

		});

		this.filterCmp = new TableCompetizioniFilter();
		this.btnResetRicComp.setVisible(false);

		final String viewNameSupp = this.viewName;
		final List<TipiAgroludosTO> suppListTipiCompetizione = this.listTipiComp;
		this.listViewComp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();
				Integer idSelected = source.getSelectionModel().getSelectedIndex();
				if(nomeTipo != null){
					TipoCompetizioneTO tipoComp = (TipoCompetizioneTO) suppListTipiCompetizione.get(idSelected);

					richiesta = getRichiesta(tipoComp, "getCompetizioniByTipo", viewNameSupp);
					risposta = getRisposta();
					eseguiRichiesta(richiesta, risposta);

					tableCompetizioni.setAll(listComp);
					filterCmp.setData(tableCompetizioni);
				}
			}
		});

		this.filterPart = new TablePartecipantiFilter();
		this.btnResetRicPart.setVisible(false);
		this.filterMdc = new TableMdcFilter();
		this.btnResetCercaMdc.setVisible(false);
		this.filterOpt = new TableOptionalFilter();
		this.btnResetRicOpt.setVisible(false);
	}

	private void setDxMdCColumn(Integer selected){
		if( selected != -1){
			MdcModel selModel = tableManagerCompetizione.getItems().get(selected);
			selModel = tableManagerCompetizione.getItems().get(selected);
			this.lblMdcNome.setText(selModel.getNome());
			this.lblMdcCognome.setText(selModel.getCognome());
			this.lblMdcEmail.setText(selModel.getEmail());
			this.lblMdcUsername.setText(selModel.getUsername());
			this.lblMdcStato.setText(selModel.getStato());
		}
	}

	private void setDxPartColumn(int selected) {
		if( selected != -1 ){
			PartModel selModel = this.tablePartecipanti.getItems().get(selected);
			selModel = this.tablePartecipanti.getItems().get(selected);
			this.lblParNome.setText(selModel.getNome());
			this.lblParCognome.setText(selModel.getCognome());
			this.lblParEmail.setText(selModel.getEmail());
			this.lblParUsername.setText(selModel.getUsername());
			this.lblParStato.setText(selModel.getStato());
			this.lblParDataSRC.setText(selModel.getDataSRC());
			this.lblParCodFisc.setText(selModel.getCf());
			this.lblParIndirizzo.setText(selModel.getIndirizzo());
			this.lblParSesso.setText(selModel.getSesso());
			this.lblParAnnoNasc.setText(selModel.getDataNasc());
			this.lblParNumTessSan.setText(selModel.getNumTessera());
		}
	}

	@FXML protected void btnCercaCompetizioniClicked(MouseEvent event) {
		this.filterCmp.updateFilteredData(this.tableCompetizioni, this.txtFilterComp);
		this.btnResetRicComp.setVisible(true);
	}

	@FXML protected void annullaRicercaCompClicked(MouseEvent event) {
		this.filterCmp.resetResearch(this.tableCompetizioni, this.txtFilterComp);
		this.btnResetRicComp.setVisible(false);
	}

	@FXML protected void annullaRicercaPartClicked(MouseEvent event){
		this.filterPart.resetResearch(this.tablePartecipanti, this.txtFilterPart);
		this.btnResetRicPart.setVisible(false);
	}

	@FXML protected void btnCercaPartClicked(MouseEvent event){
		this.filterPart.updateFilteredData(this.tablePartecipanti, this.txtFilterPart);
		this.btnResetRicPart.setVisible(true);
	}

	@FXML protected void cercaMdcClicked(MouseEvent event){
		this.filterMdc.updateFilteredData(this.tableManagerCompetizione, this.txtFilterMdc);
		this.btnResetCercaMdc.setVisible(true);
	}

	@FXML protected void annullaRicercaMdcClicked(MouseEvent event){
		this.filterMdc.resetResearch(this.tableManagerCompetizione, this.txtFilterMdc);
		this.btnResetCercaMdc.setVisible(false);
	}

	@FXML protected void btnCercaOptClicked(MouseEvent event){
		this.filterOpt.updateFilteredData(this.tableOptional, this.txtFilterOpt);
		this.btnResetRicOpt.setVisible(true);
	}

	@FXML protected void annullaRicercaOptClicked(MouseEvent event){
		this.filterOpt.resetResearch(this.tableOptional, this.txtFilterOpt);
		this.btnResetRicOpt.setVisible(false);
	}

	private void startSearch(String textFieldID){
		switch(textFieldID){
		case "txtFilterComp":
			this.filterCmp.updateFilteredData(this.tableCompetizioni, this.txtFilterComp);
			this.btnResetRicComp.setVisible(true);
			break;
		case "txtFilterPart":
			this.filterPart.updateFilteredData(this.tablePartecipanti, this.txtFilterPart);
			this.btnResetRicPart.setVisible(true);
			break;
		case "txtFilterMdC":
			this.filterMdc.updateFilteredData(this.tableManagerCompetizione, this.txtFilterMdc);
			this.btnResetCercaMdc.setVisible(true);
			break;
		};
	}

	@FXML protected void cercaKeyPressed(KeyEvent event){
		String textFieldID = ((TextField)event.getSource()).getId();

		if (event.getCode() == KeyCode.ENTER){
			this.startSearch(textFieldID);
		}
	}

	private void resetRicerche(){
		this.filterCmp.resetResearch(this.tableCompetizioni, this.txtFilterComp);
		this.btnResetRicComp.setVisible(false);
		this.filterPart.resetResearch(this.tablePartecipanti, this.txtFilterPart);
		this.btnResetRicPart.setVisible(false);
	}

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
		this.resetRicerche();
	}

	@FXML protected void btnGestOptional(MouseEvent event) {

		this.resetRicerche();
		this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);


		this.tableOptional = new TableOptional();
		this.paneTableOptional.add(this.tableOptional, 0, 1);
		this.paneTableOptional.setVisible(true);
		this.listViewOpt = new ListaViewTipi(this.listTipiOpt);
		this.paneListaTipiOpt.getChildren().add(listViewOpt);

		final String viewNameSupp = this.viewName;
		final List<TipiAgroludosTO> suppListTipiOptional = this.listTipiOpt;
		this.listViewOpt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();
				Integer idSelected = source.getSelectionModel().getSelectedIndex();
				if(nomeTipo != null){
					TipoOptionalTO tipoOpt = (TipoOptionalTO) suppListTipiOptional.get(idSelected);
					richiesta = getRichiesta(tipoOpt, "getOptionalByTipo", viewNameSupp);
					risposta = getRisposta();
					eseguiRichiesta(richiesta, risposta);
					tableOptional.setAll(listOpt);
					filterOpt.setData(tableOptional);
				}
			}

		});

		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(true);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestManComp(MouseEvent event) {
		this.resetRicerche();
		this.richiesta = this.getRichiesta("getAllManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.tableManagerCompetizione = new TableMdC(this.listMdc);
		this.filterMdc.setData(this.tableManagerCompetizione);
		this.tableManagerCompetizione.getSelectionModel().selectFirst();
		this.paneTableMdC.getChildren().add(this.tableManagerCompetizione);
		this.setDxMdCColumn(this.selectedMdC);
		this.tableManagerCompetizione.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<MdcModel>(){

					@Override
					public void changed(ObservableValue<? extends MdcModel> partModel,
							MdcModel oldMod, MdcModel newMod) {

						selectedMdC = tableManagerCompetizione.getSelectedIndex();
						setDxMdCColumn(selectedMdC);

					}

				});

		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(true);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestPart(MouseEvent event) {
		this.resetRicerche();
		this.richiesta = this.getRichiesta("getAllPartecipante", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		this.tablePartecipanti = new TablePartecipanti(this.listPart);
		this.filterPart.setData(this.tablePartecipanti);
		this.tablePartecipanti.getSelectionModel().selectFirst();
		this.paneTablePart.getChildren().add(this.tablePartecipanti);
		this.setDxPartColumn(this.selectedPart);
		this.tablePartecipanti.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<PartModel>(){

					@Override
					public void changed(ObservableValue<? extends PartModel> partModel,
							PartModel oldMod, PartModel newMod) {

						selectedPart = tablePartecipanti.getSelectedIndex();
						setDxPartColumn(selectedPart);
						if( selectedPart != -1 )
							setDxPartColumn(selectedPart);

					}

				});

		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(true);
	}

	@FXML protected void btnDisattivaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		if(optModel != null){
			OptionalTO optTO = optModel.getOptTO();
			this.richiesta = this.getRichiesta(optTO, "checkOptCmpAttive", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);
			if(!this.checkOpt){
				AgroRequest request = this.getRichiesta(optTO, "disattivaOptional", this.viewName);
				AgroResponse response = this.getRisposta();
				this.eseguiRichiesta(request, response);
			}
		}

	}

	@FXML protected void modificaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		if(optModel != null){
			OptionalTO optTO = optModel.getOptTO();
			this.richiesta = this.getRichiesta(optTO, "checkOptCmpAttive", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);
			if(!this.checkOpt)
				this.setVista("modificaOpt", optTO);
		}
	}
	@FXML protected void nuovoMdCClicked(MouseEvent event){
		this.setVista("nuovoMDC");
	}

	@FXML protected void modificaMdCClicked(MouseEvent event){
		MdcModel mdcMod = this.tableManagerCompetizione.getSelectedItem();
		if(mdcMod != null){
			ManagerDiCompetizioneTO mdcto = mdcMod.getManComp();
			this.richiesta = this.getRichiesta(mdcto, "checkMdcCmpAttive", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

			if(!this.checkMdc)
				this.setVista("modificaMDC", mdcto);
		}
	}

	@FXML protected void eliminaMdCClicked(MouseEvent event){
		MdcModel mdcMod = this.tableManagerCompetizione.getSelectedItem();
		if(mdcMod != null){
			ManagerDiCompetizioneTO mdcto = mdcMod.getManComp();
			this.richiesta = this.getRichiesta(mdcto, "checkMdcCmpAttive", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);
			if(!this.checkMdc){
				this.richiesta = this.getRichiesta(mdcto, "eliminaManagerDiCompetizione", this.viewName);
				this.risposta = this.getRisposta();
				this.eseguiRichiesta(this.richiesta, this.risposta);
			}
		}
	}

	@FXML protected void nuovoTipoCompetizioneClicked(MouseEvent event) {
		this.setVista("nuovoTipoCpt");
	}

	@FXML protected void nuovoTipoOptionalClicked(MouseEvent event) {
		this.setVista("nuovoTipoOpt");
	}

	@FXML protected void nuovoOptClicked(MouseEvent event){
		TipoOptionalTO tipoOpt = toFact.createTipoOptionalTO();
		String nome = this.listViewOpt.getSelectionModel().getSelectedItem();
		if(nome != null){
			tipoOpt.setNome(nome);
			this.setVista("nuovoOpt", tipoOpt);
		}
	}

	@FXML protected void visualizzaCertificatoSrc(MouseEvent event){
		PartModel partModel = this.tablePartecipanti.getSelectionModel().getSelectedItem();
		if(partModel != null){
			PartecipanteTO sPart = partModel.getPart();
			this.setVista("visualizzaSRC", sPart);
		}
	}

	@FXML protected void visualizzaIscrizioniClicked(MouseEvent event){
		PartModel partModel = this.tablePartecipanti.getSelectionModel().getSelectedItem();
		if(partModel != null){
			PartecipanteTO sPart = partModel.getPart();
			this.setVista("visualizzaIscrizioni", sPart);
		}
	}

	@FXML protected void menuLogout(ActionEvent event){
		this.close();
		this.setVista("login");
	}


	@FXML protected void menuEsci(ActionEvent event){
		chiusura();
	}

	private void chiusura(){
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion(resources.getString("key180"));
		question.setRequest("chiusura");
		question.setViewName(viewName);
		setVista("questionDialog", question);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.getCommandName("getAllManagerDiCompetizione") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<ManagerDiCompetizioneTO> mdcList = (List<ManagerDiCompetizioneTO>)res;
				this.listMdc = mdcList;
			}

		} else if(commandName.equals( this.getCommandName("getAllOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<OptionalTO> optList = (List<OptionalTO>)res;
				this.listOpt = optList;
			}

		} else if(commandName.equals( this.getCommandName("getAllPartecipante") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<PartecipanteTO> partList = (List<PartecipanteTO>)res;
				this.listPart = partList;
			}

		} else if(commandName.equals( this.getCommandName("modificaManagerDiCompetizione") )){

			Object res = response.getRespData();

			if(res instanceof ManagerDiCompetizioneTO){
				ManagerDiCompetizioneTO mdcTO = (ManagerDiCompetizioneTO)res;
				MdcModel mdc = this.tableManagerCompetizione.getItems().get(this.selectedMdC);
				mdc.setNome(mdcTO.getNome());
				mdc.setCognome(mdcTO.getCognome());
				mdc.setEmail(mdcTO.getEmail());
				mdc.setUsername(mdcTO.getUsername());
				mdc.setStato(mdcTO.getStatoUtente().getNome());
				setDxMdCColumn(this.selectedMdC);

				//TODO send email?
			}

		} else if( commandName.equals( this.getCommandName("getAllTipoOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiOptList = (List<TipiAgroludosTO>)res;
				this.listTipiOpt = tipiOptList;
			}

		} else if( commandName.equals( this.getCommandName("getOptionalByTipo") )){

			Object res = response.getRespData();

			if( res instanceof List<?>){
				this.listOpt = (List<OptionalTO>)res;
			}

		} else if( commandName.equals( this.getCommandName("getAllTipoCompetizione") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiCompList = (List<TipiAgroludosTO>)res;
				this.listTipiComp = tipiCompList;
			}

		} else if( commandName.equals( this.getCommandName("getCompetizioniByTipo") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				this.listComp = (List<CompetizioneTO>)res;
			}

		} else if( commandName.equals( this.getCommandName("inserisciTipoOptional") )){
			Object res = response.getRespData();

			if(res instanceof TipoOptionalTO){
				TipoOptionalTO tipo = (TipoOptionalTO)res;
				this.listViewOpt.addItem(tipo);
			}
		} else if( commandName.equals( this.getCommandName("disattivaOptional") )){
			Object res = response.getRespData();

			if(res instanceof OptionalTO){
				OptionalTO optTO = (OptionalTO)res;
				OptModel optModel = this.tableOptional.getSelectedItem();
				optModel.setNomeStato(optTO.getStatoOptional().getNome());
			}if(res instanceof String){
				ErrorMessageTO errmTO = toFact.createErrMessageTO();
				errmTO.setMessage((String) res);
				this.setVista("messageDialog", errmTO);
			}
		} else if( commandName.equals( this.getCommandName("modificaOptional") )){
			Object res = response.getRespData();

			if(res instanceof OptionalTO){
				OptionalTO optTO = (OptionalTO)res;
				OptModel optModel = this.tableOptional.getSelectedItem();
				optModel.setNome(optTO.getNome());
				optModel.setCosto(optTO.getCosto());
				optModel.setDescrizione(optTO.getDescrizione());
				optModel.setNomeStato(optTO.getStatoOptional().getNome());
				SuccessMessageTO succTO = toFact.createSuccMessageTO();
				succTO.setMessage(this.resources.getString("key167"));
				this.setVista("messageDialog", succTO);
			}if(res instanceof String){
				ErrorMessageTO errmTO = toFact.createErrMessageTO();
				errmTO.setMessage(this.resources.getString((String) res));
				this.setVista("messageDialog", errmTO);
			}
		} else if( commandName.equals( this.getCommandName("inserisciOptional") )){
			Object res = response.getRespData();

			if(res instanceof OptionalTO){
				OptionalTO optTO = (OptionalTO)res;
				this.tableOptional.addItem(optTO);
			}
		} else if( commandName.equals( this.getCommandName("inserisciTipoCompetizione") )){
			Object res = response.getRespData();

			if(res instanceof TipoCompetizioneTO){
				TipoCompetizioneTO tipo = (TipoCompetizioneTO)res;
				this.listViewComp.addItem(tipo);
			}
		} else if( commandName.equals( this.getCommandName("eliminaManagerDiCompetizione") )){
			Object res = response.getRespData();

			if(res instanceof ManagerDiCompetizioneTO){
				ManagerDiCompetizioneTO mdc = (ManagerDiCompetizioneTO)res;
				this.lblMdcStato.setText(mdc.getStatoUtente().getNome());
				SuccessMessageTO succTO = toFact.createSuccMessageTO();
				succTO.setMessage(this.resources.getString("key130"));
				this.setVista("messageDialog", succTO);
			}
		} else if( commandName.equals( this.getCommandName("nuovoManagerDiCompetizione") )){
			Object res = response.getRespData();

			if(res instanceof ManagerDiCompetizioneTO){
				ManagerDiCompetizioneTO optTO = (ManagerDiCompetizioneTO)res;
				this.tableManagerCompetizione.addItem(optTO);
			}
		}else if( commandName.equals( this.getCommandName("checkMdcCmpAttive") )){
			Object res = response.getRespData();

			if(res instanceof ManagerDiCompetizioneTO){
				this.checkMdc=false;
			}else if(res instanceof String){
				this.checkMdc=true;
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}else if( commandName.equals( this.getCommandName("checkOptCmpAttive") )){
			Object res = response.getRespData();
			if(res instanceof OptionalTO){
				this.checkOpt=false;
			}else if(res instanceof String){
				this.checkOpt=true;
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}
	}
}