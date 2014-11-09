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
import agroludos.to.ErrorMessageTO;
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
	public void initializeView(String nameView) {
		this.viewName = nameView;
		this.lblStatoOptError.setVisible(false);
		this.lblNomeOptError.setVisible(false);
		this.lblCostoOptError.setVisible(false);
	}

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
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

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

	@FXML protected void confermaModificaOptional(MouseEvent event){
		this.optional.setNome(this.txtNomeOptional.getText());
		this.optional.setDescrizione(this.txtAreaDescrizione.getText());
		BigDecimal costo = this.costoOptional.getNumber();
		this.optional.setCosto(costo.doubleValue());
		int selectedStato = this.cmbStatoOptional.getSelectionModel().getSelectedIndex();
		StatoOptionalTO stato = this.listStatiOpt.get(selectedStato);
		this.optional.setStatoOptional(stato);
		AgroRequest request = this.getRichiesta(this.optional, "modificaOptional", this.viewName);
		AgroResponse response = this.getRisposta();
		this.eseguiRichiesta(request, response);

		Object res = response.getRespData();
		if(res instanceof OptionalTO)
			this.close();
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.getCommandName("getAllStatoOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoOptionalTO> statiList = (List<StatoOptionalTO>)res;
				this.listStatiOpt = statiList;
			}
		} else if( commandName.equals( this.getCommandName("modificaOptional")) ){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;

				if(errors.hasError(this.getError("nomeKey"))){
					String nomeKey = this.getError("nomeKey");
					this.lblNomeOptError.setVisible(true);
					this.lblNomeOptError.setText(errors.getError(nomeKey));
				}

				if(errors.hasError(this.getError("costoKey"))){
					String nomeKey = this.getError("costoKey");
					this.lblCostoOptError.setVisible(true);
					this.lblCostoOptError.setText(errors.getError(nomeKey));
				}

				if(errors.hasError(this.getError("statoKey"))){
					String nomeKey = this.getError("statoKey");
					this.lblStatoOptError.setVisible(true);
					this.lblStatoOptError.setText(errors.getError(nomeKey));
				}

			}else if(res instanceof String){
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}

	}

}