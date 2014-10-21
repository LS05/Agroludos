package agroludos.presentation.views.partecipante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

public class ControllerPartSelezionaOptional extends AgroludosController{
	private String viewName;

	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;

	//Tabella Optional
	private TableOptional tableOptional;
	@FXML private GridPane paneTableOptional;
	private Map<TipoOptionalTO, List<OptionalTO>> mainData;

	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;
	@FXML private ListView<String> listViewOptional;
	private ObservableList<String> listViewData;

	private CompetizioneTO mainComp;
	private List<OptionalTO> optComp;

	private IscrizioneTO mainIscr;

	private List<TipoOptionalTO> listTipiOpt;

	private Map<String, OptionalTO> optScelti;
	private Map<String, Integer> indScelti;

	private Integer nPassi;
	private int passoCorrente;

	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){

			this.mainIscr = (IscrizioneTO) mainTO;

			this.mainComp = this.mainIscr.getCompetizione();

			this.optComp = this.mainComp.getAllOptionals();
			
			this.mainData = new HashMap<TipoOptionalTO, List<OptionalTO>>();
			
			this.listViewData = FXCollections.observableArrayList();

			this.optScelti = new HashMap<String, OptionalTO>();
			this.indScelti = new HashMap<String, Integer>();
			
			this.passoCorrente = 0;

			this.btnAvanti.setVisible(true);
			this.btnConferma.setVisible(false);
			this.btnIndietro.setVisible(true);
			this.btnIndietro.setDisable(true);
			
			this.initData();
			
			this.setLabelDialog();

			this.tableOptional = new TableOptional();
			this.paneTableOptional.getChildren().add(this.tableOptional);
			this.paneTableOptional.setVisible(true);		

			this.setTable((TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente));

			this.tableOptional.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					TipoOptionalTO tipoCorr = (TipoOptionalTO)listTipiOpt.get(passoCorrente);
					OptionalTO opt = tableOptional.getSelectedItem().getOptTO();
					optScelti.put(tipoCorr.getNome(), opt);
					indScelti.put(tipoCorr.getNome(), tableOptional.getSelectedIndex());
				}

			});
		}

	}
	
	private void initData(){
		this.listTipiOpt = new ArrayList<TipoOptionalTO>();
		
		for(OptionalTO optional : this.optComp){
			TipoOptionalTO tipo = optional.getTipoOptional();
			if(!this.mainData.containsKey(tipo)){
				this.listTipiOpt.add(tipo);
				ArrayList<OptionalTO> nList = new ArrayList<OptionalTO>();
				nList.add(optional);
				this.mainData.put(tipo, nList);
			} else {
				List<OptionalTO> eList = this.mainData.get(tipo);
				eList.add(optional);
				this.mainData.put(tipo, eList);
			}
		}
		
		int steps = this.mainData.keySet().size();
		
		this.nPassi = steps + 1;
		
		if(steps == 1){
			this.btnAvanti.setVisible(false);
			this.btnConferma.setVisible(true);
			this.btnIndietro.setDisable(true);
		}
	}
	
	private void setLabelDialog(){
		if(this.passoCorrente != (this.nPassi - 1))
			this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente).getNome());
		this.lblPassi.setText("Passo " + (this.passoCorrente + 1) + " di " + this.nPassi);
	}

	private void setTable(TipoOptionalTO tipoOpt){
		List<OptionalTO> tblList = new ArrayList<OptionalTO>();

		for(OptionalTO opt : this.optComp){
			if(opt.getTipoOptional().equals(tipoOpt)){
				tblList.add(opt);
			}
		}

		this.tableOptional.setAll(tblList);
	}

	@FXML protected void btnIndietro(MouseEvent event) { 
		this.passoCorrente--;
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);
		
		if(this.passoCorrente == 0)
			this.btnIndietro.setDisable(true);
		
		if(!this.tableOptional.visibleProperty().getValue()){
			this.tableOptional.setVisible(true);
		}

		this.setLabelDialog();

		TipoOptionalTO tipoCorr = (TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente);
		this.setTable(tipoCorr);

		if(this.indScelti.containsKey(tipoCorr.getNome())){
			this.tableOptional.getSelectionModel().select(this.indScelti.get(tipoCorr.getNome()));
		}
	}
	
	private void fillConfList(){
		this.listViewData.clear();
		for (OptionalTO opt : this.optScelti.values()) {
			this.listViewData.add(opt.getNome());
		}
	}

	@FXML protected void btnAvanti(MouseEvent event) {
		this.passoCorrente++;
		this.setLabelDialog();
		
		if(this.passoCorrente == (this.nPassi - 1)){
			this.btnAvanti.setVisible(false);
			this.tableOptional.setVisible(false);
			this.btnConferma.setVisible(true);
			fillConfList();
			this.listViewOptional.setItems(this.listViewData);
			this.listViewOptional.setVisible(true);
		} else {
			this.btnIndietro.setDisable(false);
			this.setTable((TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente));
			TipoOptionalTO tipoCorr = (TipoOptionalTO)this.listTipiOpt.get(this.passoCorrente);
			this.setTable(tipoCorr);
			
			if(this.indScelti.containsKey(tipoCorr.getNome())){
				this.tableOptional.getSelectionModel().select(this.indScelti.get(tipoCorr.getNome()));
			}
		}

	}

	@FXML protected void btnConferma(MouseEvent event) {
//		Set<OptionalTO> setOpt = new HashSet<OptionalTO>();
//		for (OptionalTO opt : this.optScelti.values()) {
//			setOpt.add(opt);
//		}
//		this.mainIscr.setOptionals(setOpt);
		for (OptionalTO opt : this.optScelti.values()) {
			this.mainIscr.addOptional(opt);
		}
		this.close();
	}

	@Override
	public String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		
	}
}