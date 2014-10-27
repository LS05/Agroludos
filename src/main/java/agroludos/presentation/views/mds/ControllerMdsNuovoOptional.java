package agroludos.presentation.views.mds;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.numberspinner.NumberSpinner;
import agroludos.to.AgroludosTO;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdsNuovoOptional extends AgroludosController implements Initializable{
	private String viewName;
	private @FXML Label lblNomeTipoOpt;
	private @FXML TextField txtNomeOptional;
	private @FXML ComboBox<String> cmbStatoOptional;
	private @FXML TextArea txtAreaDescrizione;
	private @FXML GridPane panePrezzoOptional;
	private NumberSpinner costoOptional;
	private AgroRequest richiesta;
	private AgroResponse risposta;
	private TipoOptionalTO tipoOpt;
	private List<StatoOptionalTO> listStatiOpt;
	private List<TipoOptionalTO> listTipiOpt;
	private ResourceBundle res;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.res = resources;
	}
	
	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.tipoOpt = (TipoOptionalTO)mainTO;
		this.costoOptional = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.05"), new DecimalFormat("#,##0.00"));
		this.panePrezzoOptional.add(this.costoOptional, 1, 0);
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
		double costo = this.costoOptional.getNumber().doubleValue();
		optional.setCosto(costo);
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
		SuccessTO msgNuovoOpt = toFact.createSuccessTO();
		msgNuovoOpt.setMessage(this.res.getString("key128"));
		nav.setVista("successDialog", msgNuovoOpt);
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