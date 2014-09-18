package agroludos.presentation.views.mdc;

import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;

public class ControllerMdcCompetizione extends AgroludosController {

	private CompetizioneTO cmpto;

	@FXML private GridPane paneModificaCmp;
	@FXML private GridPane paneVisualizzaCmp;
	@FXML private GridPane paneIscritti;

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
	@FXML private Label lblModificaOk;

	//bottoni annulla e modifica competizione
	@FXML private Button btnAnnullaCmp;
	@FXML private Button btnModificaCmp;
	@FXML private Button btnAnnullaModifica;
	@FXML private Button btnConfermaModifica;
	@FXML private Button btnChiudi;

	private Node source;

	private Stage stage;

	private AgroRequest richiesta;

	private AgroResponse risposta;

	//oggetti del pane di modifica
	@FXML private TextField txtModNomeCompetizione;
	@FXML private TextField txtModData;
	@FXML private ComboBox<Integer> cmbModNmin;
	@FXML private ComboBox<Integer> cmbModNmax;
	@FXML private TextField txtModCosto;
	@FXML private Label lblModTipo;
	@FXML private Label lblModStato;
	@FXML private TextArea txtModDescrizione;


	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.paneVisualizzaCmp.setVisible(true);
		this.paneModificaCmp.setVisible(false);
		this.paneIscritti.setDisable(false);
		this.lblModificaOk.setVisible(false);

		this.cmpto =(CompetizioneTO) mainTO;

		this.lblNomeCompetizione.setText(this.cmpto.getNome());
		this.lblData.setText(this.cmpto.getData().toString());
		this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
		this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
		this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.lblTipo.setText(this.cmpto.getNomeTipo());
		this.lblNiscritti.setText(Integer.toString(this.cmpto.getAllIscritti().size()));
		this.lblStato.setText(this.cmpto.getNomeStato());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());

	}

	@FXML protected void btnAnnullaCmp(MouseEvent event) {

	}

	@FXML protected void btnChiudi(MouseEvent event) {

		nav.setVista("managerDiCompetizione",this.cmpto);
		this.hideStageFromEvent(event);

	}

	@FXML protected void btnModificaCmp(MouseEvent event) {
		
		this.lblModificaOk.setVisible(false);
		nav.setVista("mostraModificaCmp", this.cmpto);
//		this.paneModificaCmp.setVisible(true);
//		this.paneVisualizzaCmp.setVisible(false);
//		this.paneIscritti.setDisable(true);
//
//		this.txtModNomeCompetizione.setText(this.cmpto.getNome());
//		this.txtModData.setText(this.cmpto.getData().toString());
//		this.txtModCosto.setText(Double.toString(this.cmpto.getCosto()));
//		this.lblModStato.setText(this.cmpto.getNomeStato());
//		this.lblModTipo.setText(this.cmpto.getNomeTipo());
//		this.txtModDescrizione.setText(this.cmpto.getDescrizione());
//
//		//popolo le combobox
//		//carico tipi nella combo box
//		//carico numeri per nmin
//		ObservableList<Integer> listNmin = FXCollections.observableArrayList();
//		for(int i=0;i<50;i++){
//			listNmin.add(i);
//		}
//		this.cmbModNmin.setItems(listNmin);
//		this.cmbModNmin.setValue(this.cmpto.getNmin());
//
//		//carico numeri per nmax
//		ObservableList<Integer> listNmax = FXCollections.observableArrayList();
//		for(int i=0;i<50;i++){
//			listNmax.add(i);
//		}
//		this.cmbModNmax.setItems(listNmax);
//		this.cmbModNmax.setValue(this.cmpto.getNmax());

	}

	@FXML protected void btnConfermaModifica(MouseEvent event) {
		this.cmpto.setCosto(Double.valueOf(this.txtModCosto.getText()));
		this.cmpto.setData(Date.valueOf(this.txtModData.getText()));
		this.cmpto.setDescrizione(this.txtModDescrizione.getText());
		this.cmpto.setNmax(this.cmbModNmax.getSelectionModel().getSelectedItem());
		this.cmpto.setNmin(this.cmbModNmin.getSelectionModel().getSelectedItem());
		this.cmpto.setNome(this.txtModNomeCompetizione.getText());
		
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(this.cmpto, "modificaCompetizione");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

//		nav.setVista("successDialog",this.cmpto);
	}
	@FXML protected void btnAnnullaModifica(MouseEvent event) {
		this.initializeView(this.cmpto);
	}

	private void hideStageFromEvent(MouseEvent event){
		this.source = (Node)event.getSource(); 
		this.stage  = (Stage)this.source.getScene().getWindow();
		this.stage.hide();
	}



	@Override
	public void initializeView() {
		// TODO Auto-generated method stub

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
