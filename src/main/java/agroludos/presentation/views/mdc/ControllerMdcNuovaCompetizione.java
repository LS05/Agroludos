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
import agroludos.to.TipoCompetizioneTO;

public class ControllerMdcNuovaCompetizione extends AgroludosController{


	private String nameView;
	private List<TipoCompetizioneTO> listTipiCmp;
	private List<StatoCompetizioneTO> listStatiCmp;
	private CompetizioneTO cmpto;
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
	private AgroResponse risposta;
	private AgroRequest richiesta;





	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeView(String viewName) {
		this.cmpto = toFact.createCompetizioneTO();
		this.nameView = viewName;

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoCompetizione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listTipi = FXCollections.observableArrayList();
		for(TipoCompetizioneTO tipoCmp: listTipiCmp){
			listTipi.add(tipoCmp.getNome());
		}
		this.cmbTipoCmp.setItems(listTipi);
		this.cmbTipoCmp.setValue(listTipi.get(0));

		this.lblInserimentoOk.setVisible(false);


	}

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
		this.richiesta = this.getRichiesta("getAllStatoCompetizione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.cmpto.setStatoCompetizione(this.listStatiCmp.get(1));

		this.cmpto.setManagerDiCompetizione((ManagerDiCompetizioneTO) ControllerUtenti.getUtente());

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.cmpto, "inserisciCompetizione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);



	}


	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
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
		}else if(commandName.equals( this.reqProperties.getProperty("inserisciCompetizione") )){
			Object res = (Object)response.getRespData();
			if(res instanceof Boolean){
				if((boolean) res){
					this.lblInserimentoOk.setVisible(true);
					this.cmpto = toFact.createCompetizioneTO();
				}
			}
		}
	}

}
