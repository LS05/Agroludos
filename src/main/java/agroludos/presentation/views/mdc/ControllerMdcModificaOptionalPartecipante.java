package agroludos.presentation.views.mdc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import agroludos.to.SuccessMessageTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoOptionalTO;

/**
 * Gestisce la view per la modifica degli optional scelti da un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerMdcModificaOptionalPartecipante extends AgroludosController implements Initializable{

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
	private ResourceBundle res;

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;
	}

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.mainIsc = (IscrizioneTO) mainTO;
			this.mainCmp = this.mainIsc.getCompetizione();

			this.optSceltiData = FXCollections.observableArrayList();

			this.risposta = this.getRisposta();
			this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
			this.eseguiRichiesta(this.richiesta, this.risposta);

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

			this.tableOptional.hideColumn(3);
			this.tableOptional.hideColumn(4);


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

			this.tableOptionalScelti.hideColumn(1);
			this.tableOptionalScelti.hideColumn(4);

			this.aggiornaTotale();
		}
	}

	/**
	 * imposta la label che tiene traccia del numero di passaggio dell'operazione
	 */
	private void setLabelDialog(){
		//label passi e label tipo optioal
		this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		this.lblPassi.setText("Passo " + (this.passoCorrente+1) + " di " + this.nPassi);
	}

	/**
	 * imposta gli optional in base al tipo in input nella tabella
	 * @param tipoOpt
	 */
	private void setTableOptional(TipoOptionalTO tipoOpt){
		List<OptionalTO> tblList = new ArrayList<OptionalTO>();

		for(OptionalTO opt : this.mainCmp.getAllOptionals()){
			if(opt.getTipoOptional().equals(tipoOpt)){
				tblList.add(opt);
			}
		}

		this.tableOptional.setAll(tblList);
	}

	/**
	 * inserisce nella tabella gli optional scelti dall'iscritto
	 */
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

	/**
	 * aggiunge alla tabella degli optional scelti un optional
	 * @param event
	 */
	@FXML protected void btnAggiungi(MouseEvent event) {
		this.btnAggiungi.setDisable(true);
		Boolean checkTipoOptional = false;
		OptModel optModSelected = this.tableOptional.getSelectedItem();
		if(optModSelected != null){
			for(OptModel optMod: this.optSceltiData){
				if(optModSelected.getNomeTipo().equals(optMod.getNomeTipo())){
					checkTipoOptional = true;
				}
			}
			if(!checkTipoOptional){
				this.optSceltiData.add(optModSelected);
				this.tableOptionalScelti.getItems().clear();
				this.tableOptionalScelti.getItems().setAll(this.optSceltiData);
			}else{
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.res.getString("key148"));
				this.setVista("messageDialog",succMessage);
			}
		}
		this.aggiornaTotale();
	}

	/**
	 * rimuove dalla lista degli optional scelti un optional
	 * @param event
	 */
	@FXML protected void btnRimuovi(MouseEvent event) {
		this.btnRimuovi.setDisable(true);

		OptModel optMod = this.tableOptionalScelti.getSelectedItem();
		if(optMod != null && this.optSceltiData.contains(optMod)){
			this.optSceltiData.remove(optMod);
			this.tableOptionalScelti.getItems().clear();
			this.tableOptionalScelti.getItems().setAll(this.optSceltiData);
		}
		this.aggiornaTotale();

	}

	/**
	 * aggiorna il totale del costo dell'iscrizione
	 */
	private void aggiornaTotale() {
		this.totale = (double) 0;

		for(OptModel optMod: this.tableOptionalScelti.getItems()){
			totale = totale + optMod.getCosto();
		}
		this.lblTotale.setText(String.valueOf(totale));

	}

	/**
	 * torna al passo precedente
	 * @param event
	 */
	@FXML protected void btnIndietro(MouseEvent event) {
		this.passoCorrente--;
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);

		if(this.passoCorrente == 0){
			this.btnIndietro.setDisable(true);
		}

		this.setLabelDialog();

		this.setTableOptional((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));
	}

	/**
	 * va al passo successivo
	 * @param event
	 */
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

	/**
	 * conferma la modifica, popola il to dell'iscrizione con le modifiche e effettua la richiesta
	 * di modifica iscrizione
	 * @param event
	 */
	@FXML protected void btnConferma(MouseEvent event) {

		this.mainIsc.clearOptionals();
		this.mainIsc.setCosto(this.totale+this.mainCmp.getCosto());
		for (OptModel optMod : this.tableOptionalScelti.getItems()) {
			this.mainIsc.addOptional(optMod.getOptTO());
		}
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.mainIsc, "modificaIscrizioneByMdc", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);

		this.close();
	}

	@Override
	public String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("getAllTipoOptional") )){
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