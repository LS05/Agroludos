package agroludos.presentation.views.mdc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import agroludos.presentation.views.components.numberspinner.NumberSpinner;
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
	@FXML private TextField txtNminCmp;
	@FXML private TextField txtNmaxCmp;
	@FXML private ComboBox<String> cmbTipoCmp;
	@FXML private TextArea txtDescrizione;
	@FXML private Label lblInserimentoOk;
	@FXML private Button btnSelezionaOptional;
	@FXML private Button btnAnnulla;
	@FXML private Button btnInserisciCmp;
	@FXML private Pane paneDataCompetizione;
	@FXML private GridPane paneCostoComp;
	
	private DatePicker dataCompPicker;
	private NumberSpinner costoComp;
	
	private List<TipoCompetizioneTO> listTipiCmp;
	private List<StatoCompetizioneTO> listStatiCmp;
	private CompetizioneTO cmpto;
	private AgroResponse risposta;
	private AgroRequest richiesta;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initializeView(String viewName) {
		this.cmpto = toFact.createCompetizioneTO();
		this.viewName = viewName;
		
		this.costoComp = new NumberSpinner(BigDecimal.ZERO, new BigDecimal("10"), new DecimalFormat("#,##0.00"));
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
		this.paneCostoComp.getChildren().add(this.costoComp);
		this.txtDescrizione.setText("");
		this.txtNmaxCmp.setText("");
		this.txtNminCmp.setText("");
		this.txtNomeCmp.setText("");

		this.dataCompPicker = new DatePicker(Locale.ENGLISH);
		this.dataCompPicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		this.dataCompPicker.getCalendarView().todayButtonTextProperty().set("Today");
		this.dataCompPicker.getCalendarView().setShowWeeks(false);
		String css = this.getClass().getResource("DatePicker.css").toExternalForm();
		this.dataCompPicker.getStylesheets().add(css);
		this.paneDataCompetizione.getChildren().add(this.dataCompPicker);
	}

	@FXML private void btnAnnulla(MouseEvent event){
		this.close();
	}

	@FXML private void btnSelezionaOptional(MouseEvent event){
		nav.setVista("selezionaOptional", this.cmpto);
	}

	@FXML private void btnInserisciCmp(MouseEvent event){
		this.cmpto.setCosto(this.costoComp.getNumber().doubleValue());
		this.cmpto.setData(this.dataCompPicker.getSelectedDate());
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

		//TODO rivedere metodo statico (sostituire con richiesta?)
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
	public String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.reqProperties.getProperty("getAllTipoCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof List<?>)
				this.listTipiCmp = (List<TipoCompetizioneTO>)res;
		}else if(commandName.equals( this.reqProperties.getProperty("getAllStatoCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof List<?>)
				this.listStatiCmp = (List<StatoCompetizioneTO>)res;
		}
	}

}