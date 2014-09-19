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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.tablemodel.MdcModel;
import agroludos.presentation.views.tablemodel.OptModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.OptionalTO;

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
	@FXML private Button btnCorsaCampestre;
	@FXML private Button btnTiroConArco;
	@FXML private Button btnNuovoTipoCompetizione;

	//gestione Optionlal
	@FXML private Button btnPranzo;
	@FXML private Button btnMerenda;
	@FXML private Button btnPernotto;
	@FXML private Button btnNuovoTipoOptional;
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
		}};

		initRequests();

		this.listaTabMdc = this.getListTabellaMdC();
		this.selectedMDC = 0;
		this.initMdcTable();
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

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("getAllManagerDiCompetizione")){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<ManagerDiCompetizioneTO> mdcList = (List<ManagerDiCompetizioneTO>)res;
				this.listMdc = mdcList;
			}
		}if(request.getCommandName().equals("getAllOptional")){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>){
				List<OptionalTO> optList = (List<OptionalTO>)res;
				this.listOpt = optList;
			}
		} else if(request.getCommandName().equals("modificaManagerDiCompetizione")){
			Object res = (Object)response.getRespData();
			if(res instanceof ManagerDiCompetizioneTO){
				ManagerDiCompetizioneTO mdcTO = (ManagerDiCompetizioneTO)res;
				MdcModel mdc = this.tableManagerCompetizione.getItems().get(this.selectedMDC);
				mdc.setNome(mdcTO.getNome());
				mdc.setCognome(mdcTO.getCognome());
				mdc.setEmail(mdcTO.getEmail());
				mdc.setUsername(mdcTO.getUsername());
				mdc.setStato(mdcTO.getNomeStatoUtente());
				setDxColumn(this.selectedMDC);
			}
		}
	}
}