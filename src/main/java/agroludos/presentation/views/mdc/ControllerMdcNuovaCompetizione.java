package agroludos.presentation.views.mdc;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.datepicker.AgroDatePicker;
import agroludos.presentation.views.components.numberspinner.NumberSpinner;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.ErrorTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.QuestionTO;
import agroludos.to.TipoCompetizioneTO;

public class ControllerMdcNuovaCompetizione extends AgroludosController implements Initializable{

	private String viewName;

	@FXML private TextField txtNomeCmp;
	@FXML private TextField txtNminCmp;
	@FXML private TextField txtNmaxCmp;
	@FXML private ComboBox<String> cmbTipoCmp;
	@FXML private TextArea txtDescrizione;
	@FXML private Button btnSelezionaOptional;
	@FXML private Button btnAnnulla;
	@FXML private Button btnInserisciCmp;
	@FXML private Pane paneDataCompetizione;
	@FXML private GridPane paneCostoComp;

	private AgroDatePicker dataCompPicker;
	private NumberSpinner costoComp;

	private List<TipoCompetizioneTO> listTipiCmp;
	private CompetizioneTO cmpto;
	private AgroResponse risposta;
	private AgroRequest richiesta;

	private ResourceBundle res;

	//label di errore
	@FXML private Label lblNomeCmpError;
	@FXML private Label lblTipoCmpError;
	@FXML private Label lblDataCmpError;
	@FXML private Label lblNminCmpError;
	@FXML private Label lblNmaxCmpError;
	@FXML private Label lblCostoCmpError;
	@FXML private Label lblSelezioneOptionalError;

	@Override
	public void initializeView(AgroludosTO mainTO) {

	}

	@Override
	public void initializeView(String nameView) {
		this.cmpto = toFact.createCompetizioneTO();
		this.viewName = nameView;

		lblNomeCmpError.setVisible(false);
		lblTipoCmpError.setVisible(false);
		lblDataCmpError.setVisible(false);
		lblNminCmpError.setVisible(false);
		lblNmaxCmpError.setVisible(false);
		lblCostoCmpError.setVisible(false);
		lblSelezioneOptionalError.setVisible(false);

		this.costoComp = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("10"), new DecimalFormat("#,##0.00"));
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta("getAllTipoCompetizione", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		ObservableList<String> listTipi = FXCollections.observableArrayList();
		for(TipoCompetizioneTO tipoCmp: listTipiCmp){
			listTipi.add(tipoCmp.getNome());
		}
		this.cmbTipoCmp.setItems(listTipi);
		this.cmbTipoCmp.setValue(listTipi.get(0));

		this.paneCostoComp.getChildren().add(this.costoComp);
		this.txtDescrizione.setText("");
		this.txtNmaxCmp.setText("0");
		this.txtNminCmp.setText("0");
		this.txtNomeCmp.setText("");

		this.dataCompPicker = new AgroDatePicker();
		this.paneDataCompetizione.getChildren().add(this.dataCompPicker.getDatePicker());
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;		
	}

	@Override
	public String getViewName() {
		return this.viewName;
	}


	@FXML protected void btnAnnulla(MouseEvent event){
		this.close();
	}

	@FXML protected void btnSelezionaOptional(MouseEvent event){
		this.setVista("selezionaOptional", this.cmpto);
	}

	@FXML protected void btnInserisciCmp(MouseEvent event){

		this.lblCostoCmpError.setVisible(false);
		this.lblDataCmpError.setVisible(false);
		this.lblNmaxCmpError.setVisible(false);
		this.lblNminCmpError.setVisible(false);
		this.lblNomeCmpError.setVisible(false);
		this.lblSelezioneOptionalError.setVisible(false);
		this.lblTipoCmpError.setVisible(false);

		this.cmpto.setCosto(this.costoComp.getNumber().doubleValue());
		this.cmpto.setData(this.dataCompPicker.getSelectedDate());
		this.cmpto.setDescrizione(this.txtDescrizione.getText());
		this.cmpto.setNmax(Integer.valueOf((this.txtNmaxCmp.getText())));
		this.cmpto.setNmin(Integer.valueOf((this.txtNminCmp.getText())));
		this.cmpto.setNome(this.txtNomeCmp.getText());

		int i = this.cmbTipoCmp.getSelectionModel().getSelectedIndex();

		this.cmpto.setTipoCompetizione(this.listTipiCmp.get(i));

		this.cmpto.setManagerDiCompetizione((ManagerDiCompetizioneTO) this.getUtente());

		if(this.cmpto.getAllOptionals().isEmpty()){

			QuestionTO question = toFact.createQuestionTO();
			question.setQuestion(this.res.getString("key171"));

			question.setDataTO(this.cmpto);
			question.setRequest("inserisciCompetizione");
			question.setViewName(this.viewName);

			this.setVista("questionDialog", question);
		}else{

			this.risposta = this.getRisposta();
			this.richiesta = this.getRichiesta(this.cmpto, "inserisciCompetizione", this.viewName);
			this.eseguiRichiesta(this.richiesta, this.risposta);
		}
	}

	private void showErrors(ErrorTO errors, Label lblError, String errorKey){
		if(errors.hasError(this.getError(errorKey))){
			String nomeKey = this.getError(errorKey);
			lblError.setVisible(true);
			lblError.setText(errors.getError(nomeKey));
		} 
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.getCommandName("getAllTipoCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof List<?>)
				this.listTipiCmp = (List<TipoCompetizioneTO>)res;
		}else if(commandName.equals( this.getCommandName("inserisciCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof ErrorTO){

				ErrorTO errors = (ErrorTO)res;

				if(errors.hasError(this.getError("nomeKey"))){
					this.showErrors(errors, this.lblNomeCmpError, "nomeKey");
				} 
				if(errors.hasError(this.getError("costoKey"))){
					this.showErrors(errors, this.lblCostoCmpError, "costoKey");
				}
				if(errors.hasError(this.getError("dataCmpKey"))){
					this.showErrors(errors, this.lblDataCmpError, "dataCmpKey");
				}
				if(errors.hasError(this.getError("nPartKey"))){
					this.showErrors(errors, this.lblNminCmpError, "nPartKey");
				}

			} else if( res instanceof String ){
				ErrorMessageTO errorMessage = toFact.createErrMessageTO();
				String msg = (String)res;
				errorMessage.setMessage(msg);
				this.setVista("messageDialog", errorMessage);
			}
		}
	}
}