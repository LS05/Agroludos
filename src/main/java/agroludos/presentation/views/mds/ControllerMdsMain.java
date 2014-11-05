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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.components.table.AgroTable;
import agroludos.presentation.views.components.table.TableCompetizioni;
import agroludos.presentation.views.components.table.TableMdC;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.table.TablePartecipanti;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.components.tablemodel.MdcModel;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.presentation.views.components.tablemodel.PartModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.PartecipanteTO;
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
	private List<ManagerDiCompetizioneTO> listMdc;
	private int selectedMdC = 0;

	//button gest competizioni
	@FXML private GridPane paneTableCmp;
	@FXML private TableCompetizioni tableCompetizioni;
	@FXML private GridPane paneListaTipiComp;
	private List<CompetizioneTO> listComp;
	private List<TipiAgroludosTO> listTipiComp;

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
	private List<PartecipanteTO> listPart;
	private int selectedPart = 0;

	//gestione Optional
	@FXML private GridPane paneListaTipiOpt;
	@FXML private GridPane paneTableOptional;
	private TableOptional tableOptional;
	private List<TipiAgroludosTO> listTipiOpt;
	private List<OptionalTO> listOpt;

	private ListaViewTipi listViewOpt;

	private ListaViewTipi listViewComp;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	private CmpModel cmpModelRow;

	private ResourceBundle resources;
	protected PartModel partModelRow;

	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.resources = res;
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);

		this.richiesta = this.getRichiesta("getAllTipoCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.tableCompetizioni = new TableCompetizioni();
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

		final String viewNameSupp = this.viewName;
		this.listViewComp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();

				TipoCompetizioneTO tipoComp = toFact.createTipoCompetizioneTO();
				tipoComp.setNome(nomeTipo);

				richiesta = getRichiesta(tipoComp, "getCompetizioneByTipo", viewNameSupp);
				risposta = getRisposta();
				eseguiRichiesta(richiesta, risposta);

				tableCompetizioni.setAll(listComp);
			}

		});
	}

	private void setDxMdCColumn(Integer selected){
		MdcModel selModel = tableManagerCompetizione.getItems().get(selected);
		this.lblMdcNome.setText(selModel.getNome());
		this.lblMdcCognome.setText(selModel.getCognome());
		this.lblMdcEmail.setText(selModel.getEmail());
		this.lblMdcUsername.setText(selModel.getUsername());
		this.lblMdcStato.setText(selModel.getStato());
	}

	private void setDxPartColumn(int selected) {
		PartModel selModel = this.tablePartecipanti.getItems().get(selected);
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

	private <T> void initSearch(final TextField searchField, final AgroTable<T> table){
		searchField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				//                table.updateTable(searchField.getText());
			}
		});
	}

	@FXML protected void btnGestComp(MouseEvent event) {

		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestOptional(MouseEvent event) {

		this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);


		this.tableOptional = new TableOptional();
		this.paneTableOptional.getChildren().add(this.tableOptional);
		this.paneTableOptional.setVisible(true);
		this.listViewOpt = new ListaViewTipi(this.listTipiOpt);
		this.paneListaTipiOpt.getChildren().add(listViewOpt);

		final String viewNameSupp = this.viewName;
		this.listViewOpt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();

				TipoOptionalTO tipoOpt = toFact.createTipoOptionalTO();
				tipoOpt.setNome(nomeTipo);

				richiesta = getRichiesta(tipoOpt, "getOptionalByTipo", viewNameSupp);
				risposta = getRisposta();
				eseguiRichiesta(richiesta, risposta);
				tableOptional.setAll(listOpt);
			}

		});

		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(true);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestManComp(MouseEvent event) {
		this.richiesta = this.getRichiesta("getAllManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.tableManagerCompetizione = new TableMdC(this.listMdc);
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
		this.richiesta = this.getRichiesta("getAllPartecipante", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		this.tablePartecipanti = new TablePartecipanti(this.listPart);
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

					}

				});


		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(true);
	}

	@FXML protected void btnDisattivaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		OptionalTO optTO = optModel.getOptTO();
		AgroRequest request = this.getRichiesta(optTO, "disattivaOptional", this.viewName);
		AgroResponse response = this.getRisposta();
		this.eseguiRichiesta(request, response);
	}

	@FXML protected void modificaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		OptionalTO optTO = optModel.getOptTO();
		this.setVista("modificaOpt", optTO);
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

	@FXML protected void nuovoMdCClicked(MouseEvent event){
		this.setVista("nuovoMDC");
	}

	@FXML protected void modificaMdCClicked(MouseEvent event){
		MdcModel mdcMod = this.tableManagerCompetizione.getSelectedItem();
		ManagerDiCompetizioneTO mdcto = this.getManagerDiCompetizione(mdcMod.getUsername());
		this.selectedMdC = this.tableManagerCompetizione.getSelectionModel().getSelectedIndex();
		this.setVista("modificaMDC", mdcto);
	}

	@FXML protected void eliminaMdCClicked(MouseEvent event){
		MdcModel mdcMod = this.tableManagerCompetizione.getSelectedItem();
		ManagerDiCompetizioneTO mdcto = this.getManagerDiCompetizione(mdcMod.getUsername());
		this.richiesta = this.getRichiesta(mdcto, "eliminaManagerDiCompetizione", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
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
		tipoOpt.setNome(nome);
		this.setVista("nuovoOpt", tipoOpt);
	}

	@FXML protected void visualizzaCertificatoSrc(MouseEvent event){
		PartModel partModel = this.tablePartecipanti.getSelectionModel().getSelectedItem();
		PartecipanteTO sPart = partModel.getPart();
		this.setVista("visualizzaSRC", sPart);
	}

	@FXML protected void visualizzaIscrizioniClicked(MouseEvent event){
		PartModel partModel = this.tablePartecipanti.getSelectionModel().getSelectedItem();
		PartecipanteTO sPart = partModel.getPart();
		this.setVista("visualizzaIscrizioni", sPart);
	}

	@FXML protected void menuLogout(ActionEvent event){
		this.close();
		this.setVista("login");
	}

	@FXML protected void menuEsci(ActionEvent event){
		this.close();
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

		} else if( commandName.equals( this.getCommandName("getCompetizioneByTipo") )){

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
		}
	}
}