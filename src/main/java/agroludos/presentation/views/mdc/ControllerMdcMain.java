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
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.ManagerDiCompetizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.SuccessTO;
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


	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
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
			cmp.setIscritti(String.valueOf(cmpTO.getAllIscrizioniAttive().size()));
			cmp.setNmax(String.valueOf(cmpTO.getNmax()));
			cmp.setNmin(String.valueOf(cmpTO.getNmin()));
			cmp.setNome(cmpTO.getNome());
			cmp.setStato(cmpTO.getStatoCompetizione().getNome());
			cmp.setTipo(cmpTO.getTipoCompetizione().getNome());			
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

	@FXML protected void menuLogout(ActionEvent event){
		this.close();
		nav.setVista("login");
	}


	@FXML protected void menuEsci(ActionEvent event){
		this.close();
		nav.termina();
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( reqProperties.getProperty("annullaCompetizione"))){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){			
				this.listaTabCmp.clear();
				CompetizioneTO cmp = (CompetizioneTO) res;
				this.initializeView(cmp.getManagerDiCompetizione());

				nav.getStage("mostraCmp").close();

				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.resources.getString("key119"));

				nav.setVista("successDialog",succMessage);
				
				EmailTO mail = toFact.createEmailTO();
				mail.setOggetto(cmp.getNome() + " annullata.");
				mail.setMittente(cmp.getManagerDiCompetizione());
				mail.setMessage("La competizione " + cmp.getNome() + "  è stata annullata.");
				
				for(IscrizioneTO iscTO: cmp.getAllIscrizioniAttive()){
					mail.addDestinatario(iscTO.getPartecipante());
				}

				this.risposta = respFact.createResponse();
				this.richiesta = this.getRichiesta(mail, "sendEmail", this.viewName);
				frontController.eseguiRichiesta(this.richiesta, this.risposta);
			}
		}else if(commandName.equals( reqProperties.getProperty("eliminaIscrizione"))){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){
				this.listaTabCmp.clear();
				this.initializeView(((IscrizioneTO) res).getCompetizione().getManagerDiCompetizione());

				nav.closeVista("mostraIscrizione");

				nav.setVista("mostraCmp",((IscrizioneTO) res).getCompetizione());

				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.resources.getString("key123"));
				nav.setVista("successDialog",succMessage);
				
			
				IscrizioneTO iscTO = ((IscrizioneTO) res);
				EmailTO mail = toFact.createEmailTO();
				mail.setOggetto("Iscrizione annullata");
				mail.setMessage(iscTO.getPartecipante().getUsername() + " abbiamo annullato l'iscrizione "
						+ "alla competizione " + iscTO.getCompetizione().getNome()
						+ "per i seguenti motivi: ");
				
				mail.addDestinatario(iscTO.getPartecipante());
				
				this.risposta = respFact.createResponse();
				this.richiesta = this.getRichiesta(mail, "sendEmail", this.viewName);
				frontController.eseguiRichiesta(this.richiesta, this.risposta);
			}
		}else if(commandName.equals( reqProperties.getProperty("inserisciCompetizione") )){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				this.listaTabCmp.clear();
				this.initializeView(((CompetizioneTO) res).getManagerDiCompetizione());			
			}
		}else if(commandName.equals(reqProperties.getProperty("modificaCompetizione"))){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){	
				this.initializeView((CompetizioneTO)res);
				nav.setVista("mostraCmp", (CompetizioneTO)res);	
				
				CompetizioneTO cmp = (CompetizioneTO) res;
				
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.resources.getString("key99"));

				nav.setVista("successDialog",succMessage);
				
				EmailTO mail = toFact.createEmailTO();
				mail.setOggetto("Modifica competizione " + cmp.getNome());
				mail.setMittente(cmp.getManagerDiCompetizione());
				mail.setMessage("La competizione " + cmp.getNome() + " ha subito modifiche,"
						+ " la invito a prendere visione.");
				
				for(IscrizioneTO iscTO: cmp.getAllIscrizioniAttive()){
					mail.addDestinatario(iscTO.getPartecipante());
				}
				
				this.risposta = respFact.createResponse();
				this.richiesta = this.getRichiesta(mail, "sendEmail", this.viewName);
				frontController.eseguiRichiesta(this.richiesta, this.risposta);

			}
		}
	}
}
