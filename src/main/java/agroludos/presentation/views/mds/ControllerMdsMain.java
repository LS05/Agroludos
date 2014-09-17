package agroludos.presentation.views.mds;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.ManagerDiCompetizioneTO;

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

	//button gest optionlal
	@FXML private Button btnPranzo;
	@FXML private Button btnMerenda;
	@FXML private Button btnPernotto;
	@FXML private Button btnNuovoTipoOptional;

	//tabella manager di competizione
	@FXML private TableView<MdcModel> tableManagerCompetizione;
	@FXML private TableColumn<MdcModel, String> mdcNomeCol;
	@FXML private TableColumn<MdcModel, String> mdcCognomeCol;
	@FXML private TableColumn<MdcModel, String> mdcEmailCol;
	@FXML private Label lblMdcNome;
	@FXML private Label lblMdcCognome;
	@FXML private Label lblMdcUsername;
	@FXML private Label lblMdcStato;
	@FXML private Label lblMdcEmail;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private List<ManagerDiCompetizioneTO> listMdc;
	private ObservableList<MdcModel> listaTabMdc;

	//setto visibile solo il primo pane

	@Override
	public void initializeView() {
		this.paneGestioneCompetizioni.setVisible(true);
		this.paneGestioneOptional.setVisible(false);
		this.paneGestioneManagerCompetizione.setVisible(false);
		this.paneGestionePartecipanti.setVisible(false);

		this.richiesta = reqFact.createSimpleRequest("getAllManagerDiCompetizione");
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.listaTabMdc = this.getListTabellaMdC();
		this.initMdcTable();
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

	private void initMdcTable(){
		this.initColumn(this.mdcNomeCol, "nome");
		this.initColumn(this.mdcCognomeCol, "cognome");
		this.initColumn(this.mdcEmailCol, "email");

		this.tableManagerCompetizione.getItems().setAll(this.listaTabMdc);
		this.tableManagerCompetizione.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<MdcModel>(){

					@Override
					public void changed(ObservableValue<? extends MdcModel> mdcModel,
							MdcModel oldMod, MdcModel newMod) {
						lblMdcNome.setText(newMod.getNome());
						lblMdcCognome.setText(newMod.getCognome());
						lblMdcEmail.setText(newMod.getEmail());
						lblMdcUsername.setText(newMod.getUsername());
						lblMdcStato.setText(newMod.getStato());
						System.out.println(newMod);
					}

				});
	}

	private ManagerDiCompetizioneTO getManagerDiCompetizione(String username){
		ManagerDiCompetizioneTO res = null;

		for(ManagerDiCompetizioneTO m : this.listMdc){
			if(m.getUsername() == username){
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
		}
	}
}