package agroludos.presentation.views.partecipante;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.EmailTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.QuestionTO;
import agroludos.to.StatoIscrizioneTO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerPartVisualizzaIscrizione extends AgroludosController implements Initializable{

	private String viewName;

	@FXML private Label lblNomeIsc;
	@FXML private Label lblCognomeIsc;
	@FXML private Label lblEmailIsc;
	@FXML private Label lblCFIsc;
	@FXML private Label lblDataNascitaIsc;
	@FXML private Label lblSessoIsc;
	@FXML private Label lblNTSIsc;
	@FXML private Label lblIndirizzoIsc;
	@FXML private Label lblDataSRCIsc;
	@FXML private Label lblDataIsc;
	@FXML private Label lblCostoIsc;
	@FXML private Label lblEliminaIscrizioneOk;
	@FXML private Button btnVisualizzaCertificato;
	@FXML private Button btnAnnullaIscrizione;
	@FXML private Button btnModificaOptionalIscrizione;
	@FXML private Button btnAggiornaCertificato;
	@FXML private GridPane paneIscrizione;
	@FXML private GridPane paneTableOptionalScelti;

	private IscrizioneTO mainIscr;

	private TableOptional tableOptional;

	private List<StatoIscrizioneTO> statiIscrizione;

	private AgroResponse risposta;
	private AgroRequest richiesta;

	private ResourceBundle res;

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof IscrizioneTO){
			this.mainIscr = (IscrizioneTO) mainTO;

			this.richiesta = this.getRichiesta("getAllStatoIscrizione", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(richiesta, risposta);

			this.lblNomeIsc.setText(this.mainIscr.getPartecipante().getNome());
			this.lblCognomeIsc.setText(this.mainIscr.getPartecipante().getCognome());
			this.lblEmailIsc.setText(this.mainIscr.getPartecipante().getEmail());
			this.lblCFIsc.setText(this.mainIscr.getPartecipante().getCf());
			this.lblDataNascitaIsc.setText(this.mainIscr.getPartecipante().getDataNasc().toString());
			this.lblSessoIsc.setText(this.mainIscr.getPartecipante().getSesso());
			this.lblNTSIsc.setText(this.mainIscr.getPartecipante().getNumTS());
			this.lblIndirizzoIsc.setText(this.mainIscr.getPartecipante().getIndirizzo());
			this.lblDataSRCIsc.setText(this.mainIscr.getPartecipante().getDataSRC().toString());
			this.lblDataIsc.setText(this.mainIscr.getData().toString());
			this.lblCostoIsc.setText(String.valueOf(this.mainIscr.getCosto()));

			this.tableOptional = new TableOptional();
			this.paneTableOptionalScelti.getChildren().add(this.tableOptional);
			this.paneTableOptionalScelti.setVisible(true);
			this.tableOptional.setAll(this.mainIscr.getAllOptionals());

			this.tableOptional.hideColumn("Stato");
			this.tableOptional.hideColumn("Descrizione");

			if(this.mainIscr.getCompetizione().getAllOptionals().isEmpty())
				this.btnModificaOptionalIscrizione.setDisable(true);
			else
				this.btnModificaOptionalIscrizione.setDisable(false);

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

	@FXML private void btnVisualizzaCertificato(){
		this.setVista("visualizzaSRC",this.mainIscr.getPartecipante());
	}

	@FXML protected void btnModificaOptionalIscrizione(MouseEvent event) {
		this.setVista("selezionaOptionalPart", this.mainIscr);
	}

	@FXML protected void btnAnnullaIscrizione(MouseEvent event) {
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion(this.res.getString("key164"));

		question.setDataTO(this.mainIscr);
		question.setRequest("eliminaIscrizione");
		question.setViewName(this.viewName);

		this.setVista("questionDialog", question);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals(this.getCommandName("getAllStatoIscrizione") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){			
				this.statiIscrizione = (List<StatoIscrizioneTO>) res;
			}
		} else if( commandName.equals(this.getCommandName("modificaIscrizione") )){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){			
				this.mainIscr = (IscrizioneTO) res;
				this.lblCostoIsc.setText(this.mainIscr.getCosto().toString());
				this.tableOptional.setAll(this.mainIscr.getAllOptionals());

				IscrizioneTO iscTO = ((IscrizioneTO) res);
				EmailTO mail = toFact.createEmailTO();
				mail.setOggetto("Modifca optional");
				mail.setMessage(iscTO.getPartecipante().getUsername() + " ha modificato l'iscrizione "
						+ "alla competizione " + iscTO.getCompetizione().getNome()
						+ " scegliendo i seguenti optional: "
						+ iscTO.getAllOptionals().toString());

				mail.addDestinatario(iscTO.getCompetizione().getManagerDiCompetizione());

				this.risposta = this.getRisposta();
				this.richiesta = this.getRichiesta(mail, "sendEmail", this.viewName);
				this.eseguiRichiesta(this.richiesta, this.risposta);
			}
		}
	}
}