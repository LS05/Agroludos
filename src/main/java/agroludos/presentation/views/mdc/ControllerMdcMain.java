package agroludos.presentation.views.mdc;

import java.util.List;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.SuccessTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcMain extends ControllerUtenti{
	
	@FXML private GridPane paneCompetizioni;

	@FXML private Button btnPaneComptizioni;
	@FXML private Button btnNuovaCompetizione;

	//tabella competizioni gestite
	@FXML private TableView<CmpModel> tableCompetizione;
	@FXML private TableColumn<CmpModel, String> cmpIdCol;
	@FXML private TableColumn<CmpModel, String> cmpNomeCol;
	@FXML private TableColumn<CmpModel, String> cmpDataCol;
	@FXML private TableColumn<CmpModel, String> cmpNIscrittiCol;
	@FXML private TableColumn<CmpModel, String> cmpNminCol;
	@FXML private TableColumn<CmpModel, String> cmpNmaxCol;
	@FXML private TableColumn<CmpModel, String> cmpTipoCol;
	@FXML private TableColumn<CmpModel, String> cmpStatoCol;
	private ObservableList<CmpModel> listaTabCmp;
	private List<CompetizioneTO> listCmp;

	private CmpModel cmpModelRow;

	private ManagerDiCompetizioneTO mdcTO;

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}

	@FXML protected void btnNuovaCompetizione(MouseEvent event) {
		nav.setVista("mostraNuovaCmp");
	}

	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}

	private void initCmpTable(){
		this.initColumn(this.cmpIdCol, "id");
		this.initColumn(this.cmpNomeCol, "nome");
		this.initColumn(this.cmpDataCol, "data");
		this.initColumn(this.cmpNIscrittiCol, "niscritti");
		this.initColumn(this.cmpNminCol, "nmin");
		this.initColumn(this.cmpNmaxCol, "nmax");
		this.initColumn(this.cmpTipoCol, "tipo");
		this.initColumn(this.cmpStatoCol, "stato");

		this.tableCompetizione.getItems().setAll(this.listaTabCmp);

		this.tableCompetizione.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					@SuppressWarnings("unchecked")
					TableView<CmpModel> table = (TableView<CmpModel>) event.getSource();
					cmpModelRow = table.getSelectionModel().getSelectedItem();
					if(cmpModelRow != null)
						nav.setVista("mostraCmp", cmpModelRow.getCompetizioneTO());
				}
			}
		});
	}
	
	private ObservableList<CmpModel> getListTabellaCmp(){
		ObservableList<CmpModel> res = FXCollections.observableArrayList();
		CmpModel modelCmp = null;

		for(CompetizioneTO cmp : this.listCmp){
			modelCmp = new CmpModel(cmp);
			res.add(modelCmp);
		}

		return res;
	}
	
	@Override
	public void initializeView(String viewName) {
		this.mdcTO = toFact.createMdCTO();
		this.mdcTO = (ManagerDiCompetizioneTO) utente;

		this.listCmp = this.mdcTO.getAllCompetizioniAttive();
		this.listaTabCmp = this.getListTabellaCmp();
		this.initCmpTable();
	}


	//chiamato dai set vista con parametro per aggiornare la tabella
	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof ManagerDiCompetizioneTO){
			this.mdcTO = toFact.createMdCTO();
			this.mdcTO = (ManagerDiCompetizioneTO) mainTO;

			this.listCmp = this.mdcTO.getAllCompetizioniAttive();
			this.listaTabCmp = this.getListTabellaCmp();
			this.initCmpTable();
		}else if(mainTO instanceof CompetizioneTO){
						
			CompetizioneTO cmpTO = (CompetizioneTO) mainTO;
			CmpModel cmp = this.tableCompetizione.getItems().get(this.tableCompetizione.getSelectionModel().getSelectedIndex());
			cmp.setCompetizioneTO(cmpTO);
			cmp.setData(cmpTO.getData().toString());
			cmp.setId(cmpTO.getId().toString());
			cmp.setNiscritti(String.valueOf(cmpTO.getAllIscrizioniAttive().size()));
			cmp.setNmax(String.valueOf(cmpTO.getNmax()));
			cmp.setNmin(String.valueOf(cmpTO.getNmin()));
			cmp.setNome(cmpTO.getNome());
			cmp.setStato(cmpTO.getStatoCompetizione().getNome());
			cmp.setTipo(cmpTO.getTipoCompetizione().getNome());			
		}
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("annullaCompetizione")){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){			
				this.listaTabCmp.clear();
				this.initializeView(((CompetizioneTO) res).getManagerDiCompetizione());
				
				nav.getStage("mostraCmp").close();
				
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage("Competizione annullata!");

				nav.setVista("successDialog",succMessage);
			}
		}else if(request.getCommandName().equals("eliminaIscrizione")){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){
				this.listaTabCmp.clear();
				this.initializeView(((IscrizioneTO) res).getCompetizione().getManagerDiCompetizione());
				
				nav.closeVista("mostraIscrizione");
				
				nav.setVista("mostraCmp",((IscrizioneTO) res).getCompetizione());
		
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage("Iscrizione eliminata!");
				nav.setVista("successDialog",succMessage);
			}
		}else if(request.getCommandName().equals( this.reqProperties.getProperty("inserisciCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				this.listaTabCmp.clear();
				this.initializeView(((CompetizioneTO) res).getManagerDiCompetizione());			
			}
		}else if(request.getCommandName().equals("modificaCompetizione")){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){	
				this.initializeView((CompetizioneTO)res);
				nav.setVista("mostraCmp", (CompetizioneTO)res);	
			}
		}
	}
}
