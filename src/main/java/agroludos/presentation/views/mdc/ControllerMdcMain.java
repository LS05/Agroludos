package agroludos.presentation.views.mdc;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.QuestionTO;
import agroludos.to.SuccessMessageTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ControllerMdcMain extends ControllerUtenti implements Initializable{

	@FXML private GridPane paneCompetizioni;

	@FXML private Button btnPaneComptizioni;
	@FXML private Button btnNuovaCompetizione;
	@FXML private MenuItem menuLogout;

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

	private ResourceBundle resources;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	private List<IscrizioneTO> listIsc;

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
		
		final Stage stage = this.getStage(this.viewName);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				chiusura();
				we.consume();
			}
		});
		
		this.mdcTO = toFact.createMdCTO();
		this.mdcTO = (ManagerDiCompetizioneTO) this.getUtente();
		
		this.risposta = this.getRisposta();
		this.richiesta = this.getRichiesta(this.mdcTO, "getCompetizioneAttiveByMdc", this.viewName);
		this.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.listaTabCmp = this.getListTabellaCmp();
		this.initCmpTable();
	}


	//chiamato dai set vista con parametro per aggiornare la tabella
	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof ManagerDiCompetizioneTO){
			this.mdcTO = toFact.createMdCTO();
			this.mdcTO = (ManagerDiCompetizioneTO) mainTO;

			this.risposta = this.getRisposta();
			this.richiesta = this.getRichiesta(this.mdcTO, "getCompetizioneAttiveByMdc", this.viewName);
			this.eseguiRichiesta(this.richiesta, this.risposta);
			
			this.listaTabCmp = this.getListTabellaCmp();
			this.initCmpTable();
		}else if(mainTO instanceof CompetizioneTO){

			CompetizioneTO cmpTO = (CompetizioneTO) mainTO;
			CmpModel cmpModel = this.tableCompetizione.getItems().get(this.tableCompetizione.getSelectionModel().getSelectedIndex());
			cmpModel.setCompetizioneTO(cmpTO);
			cmpModel.setData(cmpTO.getData().toString());
			cmpModel.setId(cmpTO.getId().toString());
			
			//richiesta per ottenere le iscrizioni attive di questa competizione
			this.richiesta = this.getRichiesta(cmpTO, "getAllIscrizioniAttiveByCmp", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);
			
			cmpModel.setNiscritti(this.listIsc.size());
			cmpModel.setNmax(String.valueOf(cmpTO.getNmax()));
			cmpModel.setNmin(String.valueOf(cmpTO.getNmin()));
			cmpModel.setNome(cmpTO.getNome());
			cmpModel.setStato(cmpTO.getStatoCompetizione().getNome());
			cmpModel.setTipo(cmpTO.getTipoCompetizione().getNome());			
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle res) {
		this.resources = res;		
	}

	@FXML protected void btnPaneComptizioni(MouseEvent event) {
		this.paneCompetizioni.setVisible(true);
	}

	@FXML protected void btnNuovaCompetizione(MouseEvent event) {
		this.setVista("mostraNuovaCmp");
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
						setVista("mostraCmp", cmpModelRow.getCompetizioneTO());
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
			
			//richiesta per ottenere le iscrizioni attive di questa competizione
			this.richiesta = this.getRichiesta(cmp, "getAllIscrizioniAttiveByCmp", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);
			
			modelCmp.setNiscritti(this.listIsc.size());
		}

		return res;
	}

	@FXML protected void menuLogout(ActionEvent event){
		this.close();
		this.setVista("login");
	}


	@FXML protected void menuEsci(ActionEvent event){
		chiusura();
	}

	private void chiusura(){
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion(resources.getString("key180"));
		question.setRequest("chiusura");
		question.setViewName(viewName);
		setVista("questionDialog", question);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.getCommandName("annullaCompetizione"))){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){			
				this.listaTabCmp.clear();
				CompetizioneTO cmp = (CompetizioneTO) res;
				this.initializeView(cmp.getManagerDiCompetizione());

				this.getStage("mostraCmp").close();
			
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.resources.getString("key119"));

				this.setVista("messageDialog",succMessage);


			}
		}else if(commandName.equals( this.getCommandName("getCompetizioneAttiveByMdc"))){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				this.listCmp = (List<CompetizioneTO>) res;
			}
		}else if(commandName.equals( this.getCommandName("eliminaIscrizioneByMdc"))){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){
				IscrizioneTO iscTO = (IscrizioneTO) res;

				this.listaTabCmp.clear();
				this.initializeView(iscTO.getCompetizione().getManagerDiCompetizione());

				closeVista("mostraIscrizione");

				this.setVista("mostraCmp",iscTO.getCompetizione());

				//mostro la view per catturare la motivazione dell'eliminazione
				this.setVista("motivoEliminazione",iscTO);
				
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.resources.getString("key123"));
				this.setVista("messageDialog",succMessage);

			}
		}else if(commandName.equals( this.getCommandName("inserisciCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				this.listaTabCmp.clear();
				this.initializeView(((CompetizioneTO) res).getManagerDiCompetizione());	
				
				this.closeVista("mostraNuovaCmp");
				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.resources.getString("key124"));
				this.setVista("messageDialog",succMessage);
				
				
			}
		}else if(commandName.equals(this.getCommandName("modificaCompetizione"))){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){	
				this.initializeView((CompetizioneTO)res);
				this.setVista("mostraCmp", (CompetizioneTO)res);	

				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.resources.getString("key99"));

				this.setVista("messageDialog",succMessage);

			}
		}else if( commandName.equals( this.getCommandName("getAllIscrizioniAttiveByCmp") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				//popolo la lista delle iscrizioni
				this.listIsc = (List<IscrizioneTO>) res;
			}
		}
	}
}
