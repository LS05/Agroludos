package agroludos.presentation.views.mdc;

import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;



public class ControllerMdcModificaCompetizione extends AgroludosController{
	
	private Node  source = null; 
    private Stage stage  = null;

	@FXML private CompetizioneTO cmpto;
	@FXML private TextField txtNome;
	@FXML private TextField txtData;
	@FXML private ComboBox<Integer> cmbNmin;
	@FXML private ComboBox<Integer> cmbNmax;
	@FXML private TextField txtCosto;
	@FXML private ComboBox<String> cmbTipoCmp;
	@FXML private TextArea txtDescrizione;

	@FXML private Button btnSelezioneOpt;
	@FXML private Button btnAnnulla;
	@FXML private Button btnConferma;
	private DataRequest richiesta;
	private AgroResponse risposta;



	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.cmpto=(CompetizioneTO) mainTO;
		this.txtNome.setText(this.cmpto.getNome());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());
		this.txtCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.txtData.setText(this.cmpto.getData().toString());


		//carico tipi nella combo box
		ObservableList<String> listTipiCmp = FXCollections.observableArrayList();
		listTipiCmp.add("Tiro con l'arco");
		listTipiCmp.add("Corsa campestre");
		this.cmbTipoCmp.setItems(listTipiCmp);
		this.cmbTipoCmp.setValue(this.cmpto.getNomeTipo());

		//carico numeri per nmin
		ObservableList<Integer> listNmin = FXCollections.observableArrayList();
		for(int i=0;i<50;i++){
			listNmin.add(i);
		}
		this.cmbNmin.setItems(listNmin);
		this.cmbNmin.setValue(this.cmpto.getNmin());
		
		//carico numeri per nmax
		ObservableList<Integer> listNmax = FXCollections.observableArrayList();
		for(int i=0;i<50;i++){
			listNmax.add(i);
		}
		this.cmbNmax.setItems(listNmax);
		this.cmbNmax.setValue(this.cmpto.getNmax());

	}

	@FXML private void btnSelezioneOpt(){
		
	}
	@FXML private void btnAnnulla(MouseEvent event){
		nav.setVista("mostraCmp", this.cmpto);
		this.source = (Node)event.getSource(); 
		this.stage  = (Stage)this.source.getScene().getWindow();
	    this.stage.close();
	}
	@FXML private void btnConferma(MouseEvent event){
		this.cmpto.setCosto(Double.valueOf(txtCosto.getText()));
		this.cmpto.setData(Date.valueOf(txtData.getText()));
		this.cmpto.setDescrizione(txtDescrizione.getText());
		this.cmpto.setNmax(cmbNmax.getSelectionModel().getSelectedItem());
		this.cmpto.setNmin(cmbNmin.getSelectionModel().getSelectedItem());
		this.cmpto.setNome(txtNome.getText());
		
		//TODO se modificacompetizione andasse a finire sulla view managerDiCompetizione??
		// oppure dopo la request modificaCompetizione richiamassimo tramite getSession la 
		//request getCompetizioneByMdc così da chiamare forword di managerDiCompetizione
		//e refreshare la tabella??
		
		
//		this.risposta = respFact.createResponse();
//		this.richiesta = reqFact.createDataRequest(this.cmpto, "modificaCompetizione");
//		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		nav.setVista("mostraCmp", this.cmpto);
		this.source = (Node)event.getSource(); 
		this.stage  = (Stage)this.source.getScene().getWindow();
	    this.stage.close();
	}


	@Override
	public void initializeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub

	}

}
