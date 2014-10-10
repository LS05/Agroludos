package agroludos.presentation.views.mdc;


import java.sql.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.StatoCompetizioneTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoCompetizioneTO;

public class ControllerMdcNuovaCompetizione extends AgroludosController{

	private String viewName;

	@FXML private TextField txtNomeCmp;
	@FXML private TextField txtDataCmp;
	@FXML private TextField txtNminCmp;
	@FXML private TextField txtNmaxCmp;
	@FXML private ComboBox<String> cmbTipoCmp;
	@FXML private TextField txtInteri;
	@FXML private TextField txtDecimali;
	@FXML private TextArea txtDescrizione;
	@FXML private Label lblInserimentoOk;
	@FXML private Button btnSelezionaOptional;
	@FXML private Button btnAnnulla;
	@FXML private Button btnInserisciCmp;
	
	private List<TipoCompetizioneTO> listTipiCmp;
	private List<StatoCompetizioneTO> listStatiCmp;
	private CompetizioneTO cmpto;
	private AgroResponse risposta;
	private AgroRequest richiesta;
	
	@FXML private void btnAnnulla(MouseEvent event){
		this.close();
	}
	
	@FXML private void btnSelezionaOptional(MouseEvent event){

	}

	@FXML private void btnInserisciCmp(MouseEvent event){
		this.cmpto.setCosto(Double.valueOf((this.txtInteri.getText()+"."+this.txtDecimali.getText())));
		this.cmpto.setData(Date.valueOf(this.txtDataCmp.getText()));
		this.cmpto.setDescrizione(this.txtDescrizione.getText());
		this.cmpto.setNmax(Integer.valueOf((this.txtNmaxCmp.getText())));
		this.cmpto.setNmin(Integer.valueOf((this.txtNminCmp.getText())));
		this.cmpto.setNome(this.txtNomeCmp.getText());

		int i = this.cmbTipoCmp.getSelectionModel().getSelectedIndex();

		this.cmpto.setTipoCompetizione(this.listTipiCmp.get(i));

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllStatoCompetizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.cmpto.setStatoCompetizione(this.listStatiCmp.get(1));

		this.cmpto.setManagerDiCompetizione((ManagerDiCompetizioneTO) ControllerUtenti.getUtente());

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.cmpto, "inserisciCompetizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		Object res = (Object)risposta.getRespData();
		if(res instanceof CompetizioneTO){

			SuccessTO succMessage = toFact.createSuccessTO();
			succMessage.setMessagge("Competizione inserita con successo");
			nav.setVista("successDialog",succMessage);
			this.close();
		}
	}
	
	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeView(String viewName) {
		this.cmpto = toFact.createCompetizioneTO();
		this.viewName = viewName;

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoCompetizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listTipi = FXCollections.observableArrayList();
		for(TipoCompetizioneTO tipoCmp: listTipiCmp){
			listTipi.add(tipoCmp.getNome());
		}
		this.cmbTipoCmp.setItems(listTipi);
		this.cmbTipoCmp.setValue(listTipi.get(0));

		this.lblInserimentoOk.setVisible(false);
		this.txtDataCmp.setText("");
		this.txtDecimali.setText("");
		this.txtDescrizione.setText("");
		this.txtInteri.setText("");
		this.txtNmaxCmp.setText("");
		this.txtNminCmp.setText("");
		this.txtNomeCmp.setText("");


	}

	@Override
	public String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.reqProperties.getProperty("getAllTipoCompetizione") )){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>)
				this.listTipiCmp = (List<TipoCompetizioneTO>)res;
		}else if(commandName.equals( this.reqProperties.getProperty("getAllStatoCompetizione") )){
			Object res = (Object)response.getRespData();
			if(res instanceof List<?>)
				this.listStatiCmp = (List<StatoCompetizioneTO>)res;
		}
	}

}