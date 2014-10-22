package agroludos.presentation.views.mdc;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.SuccessTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdcModificaOptionalPartecipante extends AgroludosController{

	private String viewName;
	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;
	@FXML private Label lblTotale;

	//Tabella Optional
	private TableOptional tableOptional;
	@FXML private GridPane paneTableOptional;

	//tabella optional scelti
	@FXML private GridPane paneTableOptionalScelti;
	private TableOptional tableOptionalScelti;

	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;
	@FXML private Button btnAggiungi;
	@FXML private Button btnRimuovi;

	private CompetizioneTO mainCmp;
	private IscrizioneTO mainIsc;
	private Integer nPassi;
	private AgroResponse risposta;
	private AgroRequest richiesta;
	private List<TipiAgroludosTO> listTipiOpt;
	private int passoCorrente;
	private ObservableList<OptModel> optSceltiData;
	private double totale;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.mainIsc = (IscrizioneTO) mainTO;
		this.mainCmp = this.mainIsc.getCompetizione();

		this.optSceltiData = FXCollections.observableArrayList();

		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.setLabelDialog();
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);
		this.btnIndietro.setVisible(true);
		this.btnIndietro.setDisable(true);
		this.btnAggiungi.setDisable(true);
		this.btnRimuovi.setDisable(true);


		//Tabella optioanl
		this.tableOptional = new TableOptional();
		this.paneTableOptional.getChildren().add(this.tableOptional);
		this.paneTableOptional.setVisible(true);		

		this.setTableOptional((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));

		this.tableOptional.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<OptModel>(){

					@Override
					public void changed(ObservableValue<? extends OptModel> optMod,
							OptModel oldMod, OptModel newMod) {
						btnAggiungi.setDisable(false);
					}

				});

		this.tableOptional.hideColumn("Stato");
		this.tableOptional.hideColumn("Tipo");


		//Tabella optional Scelti
		this.tableOptionalScelti = new TableOptional();
		this.paneTableOptionalScelti.getChildren().add(this.tableOptionalScelti);
		this.paneTableOptionalScelti.setVisible(true);		

		this.setTableOptionalScelti();

		this.tableOptionalScelti.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<OptModel>(){

					@Override
					public void changed(ObservableValue<? extends OptModel> optMod,
							OptModel oldMod, OptModel newMod) {
						btnRimuovi.setDisable(false);
					}

				});

		this.tableOptionalScelti.hideColumn("Stato");
		this.tableOptionalScelti.hideColumn("Descrizione");

		this.AggiornaTotale();

	}

	private void setLabelDialog(){
		//label passi e label tipo optioal
		this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		this.lblPassi.setText("Passo " + (this.passoCorrente+1) + " di " + this.nPassi);
	}

	private void setTableOptional(TipoOptionalTO tipoOpt){
		List<OptionalTO> tblList = new ArrayList<OptionalTO>();

		for(OptionalTO opt : this.mainCmp.getAllOptionals()){
			if(opt.getTipoOptional().equals(tipoOpt)){
				tblList.add(opt);
			}
		}

		this.tableOptional.setAll(tblList);
	}

	private void setTableOptionalScelti(){
		if(!this.mainIsc.getAllOptionals().isEmpty()){
			for(OptionalTO opt: this.mainIsc.getAllOptionals()){
				OptModel optMod = new OptModel(opt);
				this.optSceltiData.add(optMod);
			}
		}
		this.tableOptionalScelti.getItems().clear();
		this.tableOptionalScelti.getItems().setAll(this.optSceltiData);
	}


	@FXML protected void btnAggiungi(MouseEvent event) {
		this.btnAggiungi.setDisable(true);
		Boolean checkTipoOptional = false;
		OptModel optModSelected = this.tableOptional.getSelectedItem();
		if(optModSelected != null){
			for(OptModel OptMod: this.optSceltiData){
				if(optModSelected.getNomeTipo().equals(OptMod.getNomeTipo()))
					checkTipoOptional = true;
			}
			if(!checkTipoOptional){
				this.optSceltiData.add(optModSelected);
				this.tableOptionalScelti.getItems().clear();
				this.tableOptionalScelti.getItems().setAll(this.optSceltiData);
			}else{
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessagge("Puoi selezionare solo un Optional per ogni tipo!");
				nav.setVista("successDialog",succMessage);
			}
		}
		this.AggiornaTotale();
	}

	@FXML protected void btnRimuovi(MouseEvent event) {
		this.btnRimuovi.setDisable(true);

		OptModel optMod = this.tableOptionalScelti.getSelectedItem();
		if(optMod != null && this.optSceltiData.contains(optMod)){
			this.optSceltiData.remove(optMod);
			this.tableOptionalScelti.getItems().clear();
			this.tableOptionalScelti.getItems().setAll(this.optSceltiData);
		}
		this.AggiornaTotale();

	}
	
	private void AggiornaTotale() {
		this.totale = (double) 0;

		for(OptModel optMod: this.tableOptionalScelti.getItems()){
			totale = totale + optMod.getCosto();
		}
		this.lblTotale.setText(String.valueOf(totale));

	}

	

	@FXML protected void btnIndietro(MouseEvent event) {

		this.passoCorrente--;
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);
		if(this.passoCorrente == 0)
			this.btnIndietro.setDisable(true);

		this.setLabelDialog();

		this.setTableOptional((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));


	}
	@FXML protected void btnAvanti(MouseEvent event) {

		this.btnIndietro.setDisable(false);
		this.passoCorrente++;
		this.setLabelDialog();

		this.setTableOptional((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));

		if(this.passoCorrente == this.nPassi-1){
			this.btnAvanti.setVisible(false);
			this.btnConferma.setVisible(true);
		}

	}
	@FXML protected void btnConferma(MouseEvent event) {

		this.mainIsc.clearOptionals();
		this.mainIsc.setCosto(this.totale+this.mainCmp.getCosto());
		for (OptModel optMod : this.tableOptionalScelti.getItems()) {
			this.mainIsc.addOptional(optMod.getOptTO());
		}
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.mainIsc, "modificaIscrizione", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);

		this.close();
	}



	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;

	}

	@Override
	public String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.reqProperties.getProperty("getAllTipoOptional") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiOptList = (List<TipiAgroludosTO>)res;
				this.listTipiOpt = tipiOptList;
				this.nPassi = this.listTipiOpt.size();
				this.passoCorrente = 0;
			}
		}
	}
}

