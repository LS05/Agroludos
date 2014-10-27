package agroludos.presentation.views.mdc;

import java.net.URL;
import java.util.ResourceBundle;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.QuestionTO;
import agroludos.to.SuccessTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcIscrizione extends AgroludosController implements Initializable{
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


	@FXML private Button btnVisualizzaCertificato;
	@FXML private Button btnAnnullaIscrizione;
	@FXML private Button btnModificaOptionalIscrizione;


	@FXML private Label lblEliminaIscrizioneOk;
	@FXML private GridPane paneIscrizione;
	private IscrizioneTO iscto;

	@FXML private GridPane paneTableOptionalScelti;
	private TableOptional tableOptional;

	private ResourceBundle res;

	
	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;

	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.iscto =(IscrizioneTO) mainTO;



		this.lblNomeIsc.setText(this.iscto.getPartecipante().getNome());
		this.lblCognomeIsc.setText(this.iscto.getPartecipante().getCognome());
		this.lblEmailIsc.setText(this.iscto.getPartecipante().getEmail());
		this.lblCFIsc.setText(this.iscto.getPartecipante().getCf());
		this.lblDataNascitaIsc.setText(this.iscto.getPartecipante().getDataNasc().toString());
		this.lblSessoIsc.setText(this.iscto.getPartecipante().getSesso());
		this.lblNTSIsc.setText(this.iscto.getPartecipante().getNumTS());
		this.lblIndirizzoIsc.setText(this.iscto.getPartecipante().getIndirizzo());
		this.lblDataSRCIsc.setText(this.iscto.getPartecipante().getDataSRC().toString());
		this.lblDataIsc.setText(this.iscto.getData().toString());
		this.lblCostoIsc.setText(String.valueOf(this.iscto.getCosto()));

		this.tableOptional = new TableOptional();
		this.paneTableOptionalScelti.getChildren().add(this.tableOptional);
		this.paneTableOptionalScelti.setVisible(true);
		this.tableOptional.setAll(this.iscto.getAllOptionals());

		this.tableOptional.hideColumn("Stato");
		this.tableOptional.hideColumn("Descrizione");
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
		nav.setVista("visualizzaSRC",this.iscto.getPartecipante());
	}

	@FXML protected void btnAnnullaIscrizione(){
		QuestionTO question = toFact.createQuestionTO();
		question.setQuestion(this.res.getString("key159"));

		question.setDataTO(this.iscto);
		question.setRequest("eliminaIscrizione");
		question.setViewName(this.viewName);

		nav.setVista("questionDialog", question);

	}

	@FXML protected void btnModificaOptionalIscrizione(MouseEvent event) {
		//TODO
		System.out.println("da fare");
		nav.setVista("modificaOptionalPartecipante", this.iscto);
	}




	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();
		if(commandName.equals( this.reqProperties.getProperty("modificaIscrizione"))){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){			
				IscrizioneTO isc = (IscrizioneTO) res;
				this.initializeView(isc);
				
				SuccessTO succMessage = toFact.createSuccessTO();
				succMessage.setMessage(this.res.getString("key99"));

				nav.setVista("successDialog",succMessage);
			}
		}
	}
}
