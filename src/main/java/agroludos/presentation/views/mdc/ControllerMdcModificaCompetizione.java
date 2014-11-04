package agroludos.presentation.views.mdc;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
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
import agroludos.to.SuccessTO;

public class ControllerMdcModificaCompetizione extends AgroludosController implements Initializable{
	private String viewName;

	@FXML private CompetizioneTO cmpto;
	@FXML private TextField txtNome;
	@FXML private ComboBox<Integer> cmbNmin;
	@FXML private ComboBox<Integer> cmbNmax;
	@FXML private TextField txtTipoCmp;
	@FXML private TextArea txtDescrizione;
	@FXML private Button btnSelezioneOpt;
	@FXML private Button btnAnnulla;
	@FXML private Button btnConferma;
	@FXML private Pane paneDataCompetizione;
	@FXML private GridPane paneCostoComp;

	private AgroDatePicker dataCompPicker;
	private NumberSpinner costoComp;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private ResourceBundle res;

	//label di errore
	@FXML private Label lblNomeCmpError;
	@FXML private Label lblTipoCmpError;
	@FXML private Label lblDataCmpError;
	@FXML private Label lblNminCmpError;
	@FXML private Label lblNmaxCmpError;
	@FXML private Label lblCostoCmpError;


	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;	
		
		lblNomeCmpError.setVisible(false);
		lblTipoCmpError.setVisible(false);
		lblDataCmpError.setVisible(false);
		lblNminCmpError.setVisible(false);
		lblNmaxCmpError.setVisible(false);
		lblCostoCmpError.setVisible(false);
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		
		if(mainTO instanceof CompetizioneTO){
			this.cmpto = (CompetizioneTO) mainTO;
			this.costoComp = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("10"), new DecimalFormat("#,##0.00"));

			this.dataCompPicker = new AgroDatePicker();
			this.dataCompPicker.setSelectedDate(this.cmpto.getData());
			this.paneDataCompetizione.getChildren().add(this.dataCompPicker.getDatePicker());

			this.txtNome.setText(this.cmpto.getNome());
			this.txtDescrizione.setText(this.cmpto.getDescrizione());
			this.paneCostoComp.add(this.costoComp, 0, 0);
			this.costoComp.setNumber(new BigDecimal(this.cmpto.getCosto()));
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
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;		
	}

	@Override
	protected String getViewName() {
		return this.viewName;
		
	}

	@FXML private void btnSelezioneOpt(){
		this.setVista("selezionaOptional", this.cmpto);
	}

	@FXML private void btnAnnulla(MouseEvent event){
		this.close();
	}

	@FXML private void btnConferma(MouseEvent event){
		this.cmpto.setCosto(this.costoComp.getNumber().doubleValue());
		this.cmpto.setData(this.dataCompPicker.getSelectedDate());
		this.cmpto.setDescrizione(txtDescrizione.getText());
		this.cmpto.setNmax(this.cmbNmax.getSelectionModel().getSelectedItem());
		this.cmpto.setNmin(this.cmbNmin.getSelectionModel().getSelectedItem());
		this.cmpto.setNome(this.txtNome.getText());

		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(cmpto, "modificaCompetizione", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);


		Object res = this.risposta.getRespData();
		if(res instanceof CompetizioneTO){			
			SuccessTO succMessage = toFact.createSuccessTO();
			succMessage.setMessage(this.getCommandName(this.res.getString("key99")));
			this.setVista("successDialog",succMessage);
		}

		this.close();
	}



	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.getCommandName("modificaCompetizione"))){
			System.out.println("errore nella modifica");
		}
	}
}