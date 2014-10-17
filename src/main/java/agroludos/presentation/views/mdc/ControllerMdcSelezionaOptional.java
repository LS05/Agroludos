package agroludos.presentation.views.mdc;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.presentation.views.components.tablemodel.PartModel;
import agroludos.presentation.views.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdcSelezionaOptional extends AgroludosController{

	private String viewName;
	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;

	//Tabella Optional
	private TableOptional tableOptional;
	@FXML private GridPane paneTableOptional;

	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;
	@FXML private Button btnAggiungi;
	@FXML private Button btnRimuovi;
	@FXML private ListView<String> listViewOptional;

	private CompetizioneTO cmpto;
	private Integer nPassi;
	private AgroResponse risposta;
	private AgroRequest richiesta;
	private List<TipiAgroludosTO> listTipiOpt;
	private int passoCorrente;
	private List<OptionalTO> listOptScelti;
	private ObservableList<String> optionalScelti;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.cmpto = (CompetizioneTO) mainTO;

		this.optionalScelti = FXCollections.observableArrayList();
		this.listViewOptional.setItems(this.optionalScelti);

		this.listOptScelti = new ArrayList<OptionalTO>();

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

		this.tableOptional = new TableOptional();
		this.paneTableOptional.getChildren().add(this.tableOptional);
		this.paneTableOptional.setVisible(true);		

		this.setTable((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));

		this.listViewOptional.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<String>(){

					@Override
					public void changed(ObservableValue<? extends String> str,
							String oldStr, String newStr) {
						if(listViewOptional.getSelectionModel().getSelectedItem() != null)
							btnRimuovi.setDisable(false);
					}

				});
		this.tableOptional.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<OptModel>(){

					@Override
					public void changed(ObservableValue<? extends OptModel> optMod,
							OptModel oldMod, OptModel newMod) {
						btnAggiungi.setDisable(false);
					}

				});

	}

	private void setLabelDialog(){
		//label passi e label tipo optioal
		this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		this.lblPassi.setText("Passo " + (this.passoCorrente+1) + " di " + this.nPassi);
	}

	private void setTable(TipoOptionalTO tipoOpt){
		this.tableOptional.setAll(tipoOpt.getAllOptionalAttivi());
	}

	@FXML protected void btnAggiungi(MouseEvent event) {
		this.btnAggiungi.setDisable(true);
		OptionalTO opt = toFact.createOptionalTO();
		opt = this.tableOptional.getSelectedItem().getOptTO();
		String optList = ("[" + opt.getTipoOptional().getNome() +
				" - " + opt.getCosto() + "] " + opt.getNome());
		if(!this.optionalScelti.contains(optList)){
			this.optionalScelti.add(optList);

			this.listViewOptional.setItems(this.optionalScelti);
			this.listOptScelti.add(opt);
		}
	}

	@FXML protected void btnRimuovi(MouseEvent event) {
		this.btnRimuovi.setDisable(true);
		String nomeOpt = this.listViewOptional.getSelectionModel().getSelectedItem();
		int indexOpt = this.listViewOptional.getSelectionModel().getSelectedIndex();
		this.optionalScelti.remove(nomeOpt);
		this.listOptScelti.remove(indexOpt);

	}

	@FXML protected void btnIndietro(MouseEvent event) {

		this.passoCorrente--;
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);
		if(this.passoCorrente == 0)
			this.btnIndietro.setDisable(true);

		this.setLabelDialog();

		this.setTable((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));


	}
	@FXML protected void btnAvanti(MouseEvent event) {

		this.btnIndietro.setDisable(false);
		this.passoCorrente++;
		this.setLabelDialog();

		this.setTable((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente));

		if(this.passoCorrente == this.nPassi-1){
			this.btnAvanti.setVisible(false);
			this.btnConferma.setVisible(true);
		}

	}
	@FXML protected void btnConferma(MouseEvent event) {

		//TODO aggiungere tutti gli optional scelti
		for (OptionalTO opt: this.listOptScelti)
			this.cmpto.addOptional(opt);
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