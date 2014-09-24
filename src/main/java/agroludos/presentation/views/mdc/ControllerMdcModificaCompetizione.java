package agroludos.presentation.views.mdc;

import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;

public class ControllerMdcModificaCompetizione extends AgroludosController{
	
	

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
	private AgroRequest richiesta;
	private AgroResponse risposta;



	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.cmpto=(CompetizioneTO) mainTO;
		this.txtNome.setText(this.cmpto.getNome());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());
		this.txtCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.txtData.setText(this.cmpto.getData().toString());
		
		this.txtTipoCmp.setText(this.cmpto.getTipoCompetizione().getNome());

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
		this.close();
	}
	@FXML private void btnConferma(MouseEvent event){
		this.cmpto.setCosto(Double.valueOf(txtCosto.getText()));
		this.cmpto.setData(Date.valueOf(txtData.getText()));
		this.cmpto.setDescrizione(txtDescrizione.getText());
		this.cmpto.setNmax(cmbNmax.getSelectionModel().getSelectedItem());
		this.cmpto.setNmin(cmbNmin.getSelectionModel().getSelectedItem());
		this.cmpto.setNome(txtNome.getText());
		
		
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(cmpto, "modificaCompetizione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.close();
		
	}

	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;
		
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("modificaCompetizione")){
			System.out.println("errore nella modifica");
		}

	}
	private String nameView;
	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}
}
