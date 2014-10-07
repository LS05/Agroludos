package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsNuovoOptional extends AgroludosController{
	private String viewName;
	private @FXML Label lblNomeTipoOpt;
	private @FXML TextField txtNomeOptional;
	private @FXML ComboBox<Double> cmbPrezzoOptional;
	private @FXML ComboBox<String> cmbStatoOptional;
	private @FXML TextArea txtAreaDescrizione;
	private AgroRequest richiesta;
	private AgroResponse risposta;
	private TipoOptionalTO tipoOpt;
	private List<StatoOptionalTO> listStatiOpt;
	private List<TipoOptionalTO> listTipiOpt;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.tipoOpt = (TipoOptionalTO)mainTO;
		this.lblNomeTipoOpt.setText(this.tipoOpt.getNome());

		this.richiesta = this.getRichiesta("getAllStatoOptional", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listStati = FXCollections.observableArrayList();
		for(StatoOptionalTO stato : this.listStatiOpt){
			listStati.add(stato.getNome());
		}
		this.cmbStatoOptional.setItems(listStati);
	}

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@FXML protected void confermaNuovoOptional(MouseEvent event){
		OptionalTO optional = toFact.createOptionalTO();

		//		optional.setCosto(this.cmbPrezzoOptional.getValue());
		optional.setCosto(20.6);
		optional.setDescrizione(this.txtAreaDescrizione.getText());
		optional.setNome(this.txtNomeOptional.getText());
		
		TipoOptionalTO tipoOpt = null;
		for(TipoOptionalTO tipo : this.listTipiOpt){
			if(tipo.getNome().equalsIgnoreCase(this.tipoOpt.getNome()))
				tipoOpt = tipo;
		}
		optional.setTipoOptional(tipoOpt);
		
		int selectedStato = this.cmbStatoOptional.getSelectionModel().getSelectedIndex();
		StatoOptionalTO statoOpt = this.listStatiOpt.get(selectedStato);
		optional.setStatoOptional(statoOpt);

		this.richiesta = this.getRichiesta(optional, "inserisciOptional", this.viewName);
		this.risposta = respFact.createResponse();
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( this.reqProperties.getProperty("getAllStatoOptional").equals(commandName) ){
			Object res = response.getRespData();
			if( res  instanceof List<?>){
				this.listStatiOpt = (List<StatoOptionalTO>)res;
			}
		} else if( this.reqProperties.getProperty("getAllTipoOptional").equals(commandName) ){
			Object res = response.getRespData();
			if( res  instanceof List<?>){
				this.listTipiOpt = (List<TipoOptionalTO>)res;
			}
		}

	}
}