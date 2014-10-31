package agroludos.presentation.views.mds;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.views.components.numberspinner.NumberSpinner;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.ErrorTO;
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;

public class ControllerMdsModificaOptional extends AgroludosController{
	private String viewName;
	private @FXML Label lblNomeTipoOpt;
	private @FXML Label lblStatoOptError;
	private @FXML Label lblNomeOptError;
	private @FXML Label lblCostoOptError;
	private @FXML TextField txtNomeOptional;
	private @FXML ComboBox<String> cmbStatoOptional;
	private @FXML TextArea txtAreaDescrizione;
	private @FXML GridPane paneCostoOptional;

	private NumberSpinner costoOptional;
	private OptionalTO optional;
	private List<StatoOptionalTO> listStatiOpt;
	private AgroRequest richiesta;
	private AgroResponse risposta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof OptionalTO){

			this.optional = (OptionalTO)mainTO;

			this.costoOptional = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.05"), new DecimalFormat("#,##0.00"));
			BigDecimal costo = BigDecimal.valueOf(this.optional.getCosto());
			this.costoOptional.setNumber(costo);
			this.paneCostoOptional.add(this.costoOptional, 1, 0);

			this.txtNomeOptional.setText(this.optional.getNome());

			this.richiesta = this.getRichiesta("getAllStatoOptional", this.viewName);
			this.risposta = respFact.createResponse();
			frontController.eseguiRichiesta(this.richiesta, this.risposta);

			ObservableList<String> listStati = FXCollections.observableArrayList();
			for(StatoOptionalTO stato : this.listStatiOpt){
				listStati.add(stato.getNome());
			}
			this.cmbStatoOptional.setItems(listStati);
			String nomeStato = this.optional.getStatoOptional().getNome();
			this.cmbStatoOptional.setValue(nomeStato);

			this.txtAreaDescrizione.setText(this.optional.getDescrizione());

			String tipoOpt = this.optional.getTipoOptional().getNome();
			this.lblNomeTipoOpt.setText(tipoOpt);

		}
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		lblStatoOptError.setVisible(false);
		lblNomeOptError.setVisible(false);
		lblCostoOptError.setVisible(false);
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@FXML protected void confermaModificaOptional(MouseEvent event){
		this.optional.setNome(this.txtNomeOptional.getText());
		this.optional.setDescrizione(this.txtAreaDescrizione.getText());
		BigDecimal costo = this.costoOptional.getNumber();
		this.optional.setCosto(costo.doubleValue());
		int selectedStato = this.cmbStatoOptional.getSelectionModel().getSelectedIndex();
		StatoOptionalTO stato = this.listStatiOpt.get(selectedStato);
		this.optional.setStatoOptional(stato);
		AgroRequest request = this.getRichiesta(this.optional, "modificaOptional", this.viewName);
		AgroResponse response = respFact.createResponse();
		frontController.eseguiRichiesta(request, response);
	}



	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( reqProperties.getProperty("getAllStatoOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoOptionalTO> statiList = (List<StatoOptionalTO>)res;
				this.listStatiOpt = statiList;
			}
		} else if( commandName.equals( reqProperties.getProperty("modificaOptional")) ){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				//				ErrorTO errors = (ErrorTO)res;

				//				if(errors.hasError(rulesProperties.getProperty("nomeKey"))){
				//					String nomeKey = rulesProperties.getProperty("nomeKey");
				//					this.lblNomeError.setVisible(true);
				//					this.lblNomeError.setText(errors.getError(nomeKey));
				//				} 


			}
		}

	}

}