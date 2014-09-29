package agroludos.presentation.views.mds;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.tablemodel.MdcModel;
import agroludos.presentation.views.tablemodel.OptModel;
import agroludos.presentation.views.tablemodel.PartModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoCompetizioneTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsMain extends ControllerUtenti{
	private String nameView;

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
	@FXML private Button btnNuovoTipoCompetizione;
	@FXML private GridPane paneListaTipiComp;
	private List<CompetizioneTO> listComp;
	private List<TipiAgroludosTO> listTipiComp;

	//gestione partecipanti
	@FXML private GridPane paneTablePart;
	@FXML private TablePartecipanti tablePartecipanti;
	@FXML private Button btnMostraSRC;
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
	@FXML private Button btnIscrPar;
	private List<PartecipanteTO> listPart;
	private int selectedPart = 0;

	//gestione Optional
	@FXML private GridPane paneListaTipiOpt;
	@FXML private Button btnNuovoTipoOptional;
	@FXML private Button btnDisattivaOptional;
	@FXML private GridPane paneTableOptional;
	private TableOptional tableOptional;
	private List<TipiAgroludosTO> listTipiOpt;
	private List<OptionalTO> listOpt;

	private AgroRequest richiesta;
	private AgroResponse risposta;
	private List<String> richieste;

	private void setDxMdCColumn(Integer selected){
		MdcModel selModel = tableManagerCompetizione.getItems().get(selected);
		this.lblMdcNome.setText(selModel.getNome());
		this.lblMdcCognome.setText(selModel.getCognome());
		this.lblMdcEmail.setText(selModel.getEmail());
		this.lblMdcUsername.setText(selModel.getUsername());
		this.lblMdcStato.setText(selModel.getStato());
	}

	@SuppressWarnings("serial")
	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;

		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);

		this.richieste = new ArrayList<String>(){{
			this.add("getAllManagerDiCompetizione");
			this.add("getAllPartecipante");
			this.add("getAllTipoOptional");
			this.add("getAllTipoCompetizione");
		}};

		initRequests();

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

		//		this.initPartTable();

		this.tableOptional = new TableOptional();
		this.paneTableOptional.getChildren().add(this.tableOptional);
		this.paneTableOptional.setVisible(true);
		ListaViewTipi listViewOpt = new ListaViewTipi(this.listTipiOpt);
		this.paneListaTipiOpt.getChildren().add(listViewOpt);

		final String viewName = nameView;
		listViewOpt.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();

				TipoOptionalTO tipoOpt = toFact.createTipoOptionalTO();
				tipoOpt.setNome(nomeTipo);

				richiesta = getRichiesta(tipoOpt, "getOptionalByTipo", viewName);
				risposta = respFact.createResponse();
				frontController.eseguiRichiesta(richiesta, risposta);
				tableOptional.setAll(listOpt);
			}

		});
		
		this.tableCompetizioni = new TableCompetizioni();
		this.paneTableCmp.getChildren().add(this.tableCompetizioni);
		ListaViewTipi listViewComp = new ListaViewTipi(this.listTipiComp);
		this.paneListaTipiComp.getChildren().add(listViewComp);


		listViewComp.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				ListView<String> source = (ListView<String>)event.getSource();
				String nomeTipo = source.getSelectionModel().getSelectedItem();

				TipoCompetizioneTO tipoComp = toFact.createTipoCompetizioneTO();
				tipoComp.setNome(nomeTipo);

				richiesta = getRichiesta(tipoComp, "getCompetizioneByTipo", viewName);
				risposta = respFact.createResponse();
				frontController.eseguiRichiesta(richiesta, risposta);
				tableCompetizioni.setAll(listComp);
			}

		});
	}

	private void initRequests(){
		for(String req : this.richieste){
			this.richiesta = this.getRichiesta(req, this.nameView);
			this.risposta = respFact.createResponse();
			frontController.eseguiRichiesta(this.richiesta, this.risposta);
		}
	}

	//----------------Main View--------------------

	@FXML protected void btnGestComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestOptional(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(true);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestManComp(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(true);
		this.paneGestionePartecipanti.setVisible(false);
	}

	@FXML protected void btnGestPart(MouseEvent event) {
		this.paneGestioneCompetizioni.setVisible(false);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneMdC.setVisible(false);
		this.paneGestionePartecipanti.setVisible(true);
	}

	@FXML protected void btnDisattivaOptionalClicked(MouseEvent event){
		OptModel optModel = this.tableOptional.getSelectionModel().getSelectedItem();
		OptionalTO optTO = optModel.getOptTO();
		AgroRequest request = this.getRichiesta(optTO, "disattivaOptional", this.nameView);
		AgroResponse response = respFact.createResponse();
		frontController.eseguiRichiesta(request, response);
	}

	//--------------------Gest Man Competizione ---------------

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

	@FXML protected void modificaManagerCompetizione(MouseEvent event){

		MdcModel mdcMod = this.tableManagerCompetizione.getSelectedItem();
		ManagerDiCompetizioneTO mdcto = this.getManagerDiCompetizione(mdcMod.getUsername());
		this.selectedMdC = this.tableManagerCompetizione.getSelectionModel().getSelectedIndex();
		nav.setVista("modificaMDC", mdcto);
	}

	//--------------------Gest Competizioni View---------------

	@FXML protected void btnNuovoTipoCompetizione(MouseEvent event) {
	}

	//--------------------Gest Optional View---------------

	@FXML protected void btnNuovoTipoOptional(MouseEvent event) {
	}

	@FXML protected void visualizzaCertificatoSrc(MouseEvent event){
		PartModel partModel = this.tablePartecipanti.getSelectionModel().getSelectedItem();
		PartecipanteTO sPart = partModel.getPart();
		nav.setVista("visualizzaSRC", sPart);
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

	//	private void initPartTable(){
	//
	//		this.tablePartecipanti.getSelectionModel().select(0);
	//
	//		this.setDxPartColumn(this.selectedPart);
	//
	//		this.tablePartecipanti.getSelectionModel().selectedItemProperty().addListener(
	//				new ChangeListener<PartModel>(){
	//
	//					@Override
	//					public void changed(ObservableValue<? extends PartModel> partModel,
	//							PartModel oldMod, PartModel newMod) {
	//
	//						selectedPart = tablePartecipanti.getSelectionModel().getSelectedIndex();
	//						setDxPartColumn(selectedPart);
	//					}
	//
	//				});
	//	}	

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.reqProperties.getProperty("getAllManagerDiCompetizione") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<ManagerDiCompetizioneTO> mdcList = (List<ManagerDiCompetizioneTO>)res;
				this.listMdc = mdcList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("getAllOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<OptionalTO> optList = (List<OptionalTO>)res;
				this.listOpt = optList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("getAllPartecipante") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<PartecipanteTO> mdcList = (List<PartecipanteTO>)res;
				this.listPart = mdcList;
			}

		} else if(commandName.equals( this.reqProperties.getProperty("modificaManagerDiCompetizione") )){

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
			}

		} else if( commandName.equals( this.reqProperties.getProperty("getAllTipoOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiOptList = (List<TipiAgroludosTO>)res;
				this.listTipiOpt = tipiOptList;
			}

		} else if( commandName.equals( this.reqProperties.getProperty("getOptionalByTipo") )){

			Object res = response.getRespData();

			if( res instanceof List<?>){
				this.listOpt = (List<OptionalTO>)res;
			}

		} else if( commandName.equals( this.reqProperties.getProperty("getAllTipoCompetizione") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiCompList = (List<TipiAgroludosTO>)res;
				this.listTipiComp = tipiCompList;
			}

		} else if( commandName.equals( this.reqProperties.getProperty("getCompetizioneByTipo") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				this.listComp = (List<CompetizioneTO>)res;
			}

		}
	}
}