package agroludos.presentation.views.mds;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
import agroludos.to.OptionalTO;
import agroludos.to.StatoOptionalTO;

public class ControllerMdsModificaOptional extends AgroludosController{
	private String viewName;
	private @FXML TextField txtNomeOptional;
	private @FXML ComboBox<String> cmbStatoOptional;
	private @FXML TextArea txtAreaDescrizione;
	private @FXML Label lblNomeTipoOpt;
	private @FXML GridPane mainPane;
	private OptionalTO optional;
	private List<StatoOptionalTO> listStatiOpt;
	private AgroRequest richiesta;
	private AgroResponse risposta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		NumberSpinner decimalFormat = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("0.05"), new DecimalFormat("#,##0.00"));
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		Label test = new Label("Prova");
		test.maxWidth(292);
		root.add(test, 0, 1);
		root.add(decimalFormat, 1, 1);
		this.mainPane.add(root, 0, 3);

		this.optional = (OptionalTO)mainTO;
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

	@FXML protected void confermaModificaOptional(MouseEvent event){
		this.optional.setNome(this.txtNomeOptional.getText());
		this.optional.setDescrizione(this.txtAreaDescrizione.getText());
		//		this.optional.setCosto(costo);

		int selectedStato = this.cmbStatoOptional.getSelectionModel().getSelectedIndex();
		StatoOptionalTO stato = this.listStatiOpt.get(selectedStato);
		this.optional.setStatoOptional(stato);
		AgroRequest request = this.getRichiesta(this.optional, "modificaOptional", this.viewName);
		AgroResponse response = respFact.createResponse();
		frontController.eseguiRichiesta(request, response);
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.reqProperties.getProperty("getAllStatoOptional") )){

			Object res = response.getRespData();

			if(res instanceof List<?>){
				List<StatoOptionalTO> statiList = (List<StatoOptionalTO>)res;
				this.listStatiOpt = statiList;
			}
		}

	}

}