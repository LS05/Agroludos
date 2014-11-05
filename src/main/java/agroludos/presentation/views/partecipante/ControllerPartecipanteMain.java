package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.components.table.TableCompetizioni;
import agroludos.presentation.views.components.table.TableIscrizioni;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.presentation.views.utenti.ControllerUtenti;
import agroludos.to.CompetizioneTO;
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;
import agroludos.to.SuccessMessageTO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerPartecipanteMain extends ControllerUtenti implements Initializable{
	private String viewName;

	@FXML private GridPane paneCompetizioni;
	@FXML private GridPane paneIscrizioni;
	@FXML private Button btnGestManComp; 
	@FXML private Button btnGestIscrizioni;
	@FXML private Button btnSelezionaOpt;
	private TableIscrizioni tableIscrizioni;
	private TableCompetizioni tableCompetizioni;

	private PartecipanteTO currUser;

	private AgroRequest richiesta;
	private AgroResponse risposta;

	private List<CompetizioneTO> listComp;

	private CmpModel cmpModelRow;
	private IscModel iscModelRow;

	private ResourceBundle res;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

		//TODO Richiesta iniziale per verificare la validità del
		//certificato SRC.

		this.currUser = (PartecipanteTO)this.getUtente();
		this.tableIscrizioni = new TableIscrizioni();
		this.tableCompetizioni = new TableCompetizioni();
		this.tableCompetizioni.hideColumn(3);
		this.tableIscrizioni.setAll(this.currUser.getAllIscrizioniAttive());
		this.paneIscrizioni.add(this.tableIscrizioni, 0, 1);
		this.tableIscrizioni.hideColumn(4);
		this.richiesta = this.getRichiesta("getCompetizioniAttive", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		this.tableCompetizioni.setAll(this.listComp);
		this.paneCompetizioni.add(this.tableCompetizioni, 0, 1);

		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);

		this.tableIscrizioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					TableView<IscModel> table = (TableView<IscModel>) event.getSource();
					iscModelRow = table.getSelectionModel().getSelectedItem();
					if(iscModelRow != null)
						setVista("partMostraIscrizione", iscModelRow.getIscrizioneTO());
				}
			}
		});

		this.tableCompetizioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() > 1) {
					TableView<CmpModel> table = (TableView<CmpModel>) event.getSource();
					cmpModelRow = table.getSelectionModel().getSelectedItem();
					if(cmpModelRow != null)
						setVista("partMostraCompetizione", cmpModelRow.getCompetizioneTO());
				}
			}
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle resources) {
		this.res = resources;		
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@FXML protected void btnGestComp(MouseEvent event) {
		this.richiesta = this.getRichiesta("getCompetizioniAttive", this.viewName);
		this.risposta = this.getRisposta();
		this.eseguiRichiesta(this.richiesta, this.risposta);
		this.tableCompetizioni.setAll(this.listComp);
		this.paneCompetizioni.setVisible(true);
		this.paneIscrizioni.setVisible(false);
	}

	@FXML protected void btnGestIscrizioni(MouseEvent event) {
		this.tableIscrizioni.setAll(this.currUser.getAllIscrizioniAttive());
		this.paneCompetizioni.setVisible(false);
		this.paneIscrizioni.setVisible(true);
	}

	@FXML protected void menuLogout(ActionEvent event){
		this.close();
		this.setVista("login");
	}

	@FXML protected void menuEsci(ActionEvent event){
		this.close();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("getCompetizioniAttive") )){
			Object res = response.getRespData();

			if(res instanceof List<?>){
				this.listComp = (List<CompetizioneTO>)res;
			}
		}else if(commandName.equals(this.getCommandName("eliminaIscrizione"))){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){
				this.tableIscrizioni.setAll(this.currUser.getAllIscrizioniAttive());

				SuccessMessageTO succMessage = toFact.createSuccMessageTO();
				succMessage.setMessage(this.res.getString("key123"));
				this.setVista("messageDialog",succMessage);

				IscrizioneTO iscTO = ((IscrizioneTO) res);
				EmailTO mail = toFact.createEmailTO();
				mail.setOggetto("Iscrizione annullata");
				mail.setMessage(iscTO.getPartecipante().getUsername() + " ha annullato l'iscrizione "
						+ "alla competizione " + iscTO.getCompetizione().getNome());

				mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

				this.risposta = this.getRisposta();
				this.richiesta = this.getRichiesta(mail, "sendEmail", this.viewName);
				this.eseguiRichiesta(this.richiesta, this.risposta);

			}
		}
	}
}