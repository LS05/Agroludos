package agroludos.presentation.views.mdc;

import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.req.DataRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.UtenteTO;



public class ControllerMdcModificaCompetizione extends AgroludosController{
	
	private Node  source = null; 
    private Stage stage  = null;

	@FXML private CompetizioneTO cmpto;
	@FXML private TextField txtNome;
	@FXML private TextField txtData;
	@FXML private ComboBox<Integer> cmbNmin;
	@FXML private ComboBox<Integer> cmbNmax;
	@FXML private TextField txtCosto;
	@FXML private TextField txtTipoCmp;
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
		
		this.txtTipoCmp.setText(this.cmpto.getNomeTipo());

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
		
		this.stage = (Stage)this.btnConferma.getScene().getWindow();
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent we) {
	            stage.hide();
	        }
	    });

	}

	@FXML private void btnSelezioneOpt(){
		
	}
	@FXML private void btnAnnulla(MouseEvent event){
		this.hideStageFromEvent(event);
	}
	@FXML private void btnConferma(MouseEvent event){
		this.cmpto.setCosto(Double.valueOf(txtCosto.getText()));
		this.cmpto.setData(Date.valueOf(txtData.getText()));
		this.cmpto.setDescrizione(txtDescrizione.getText());
		this.cmpto.setNmax(cmbNmax.getSelectionModel().getSelectedItem());
		this.cmpto.setNmin(cmbNmin.getSelectionModel().getSelectedItem());
		this.cmpto.setNome(txtNome.getText());
		
		
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(this.cmpto, "modificaCompetizione");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.hideStageFromEvent(event);
		
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
			System.out.println("errore nella modifica");
		}

	}

}
