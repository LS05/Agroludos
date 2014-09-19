package agroludos.presentation.views.mdc;

import java.sql.Date;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;

public class ControllerMdcCompetizione extends AgroludosController {

	private CompetizioneTO cmpto;
	
	@FXML private GridPane paneVisualizzaCmp;
	@FXML private GridPane paneIscritti;

	//label della competizione da visualizzare
	@FXML private Label lblNomeCompetizione;
	@FXML private Label lblData;
	@FXML private Label lblNmin;
	@FXML private Label lblNmax;
	@FXML private Label lblCosto;
	@FXML private Label lblTipo;
	@FXML private Label lblNiscritti;
	@FXML private Label lblStato;
	@FXML private TextArea txtDescrizione;
	//mostra messaggio di modifica avvenuta
	@FXML private Label lblModificaOk;
	//mostra il messaggio di annullamento avvenuto
	@FXML private Label lblAnnullaOk;

	//bottoni annulla e modifica competizione
	@FXML private Button btnAnnullaCmp;
	@FXML private Button btnModificaCmp;
	@FXML private Button btnChiudi;

	private Node source;

	private Stage stage;

	private AgroRequest richiesta;

	private AgroResponse risposta;


	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.paneVisualizzaCmp.setVisible(true);
		this.paneIscritti.setDisable(false);
		this.lblModificaOk.setVisible(false);

		this.cmpto =(CompetizioneTO) mainTO;

		this.lblNomeCompetizione.setText(this.cmpto.getNome());
		this.lblData.setText(this.cmpto.getData().toString());
		this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
		this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
		this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.lblTipo.setText(this.cmpto.getNomeTipo());
		this.lblNiscritti.setText(Integer.toString(this.cmpto.getAllIscritti().size()));
		this.lblStato.setText(this.cmpto.getNomeStato());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());
		
		this.stage = (Stage)this.paneVisualizzaCmp.getScene().getWindow();
		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent we) {
	        	nav.setVista("managerDiCompetizione",cmpto);
	            stage.hide();
	        }
	    });

	}

	@FXML protected void btnAnnullaCmp(MouseEvent event) {

		System.out.println("Confermi? si...");
		this.risposta = respFact.createResponse();
		this.richiesta = reqFact.createDataRequest(this.cmpto, "annullaCompetizione");
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
	}

	@FXML protected void btnChiudi(MouseEvent event) {

		nav.setVista("managerDiCompetizione",this.cmpto);
		this.hideStageFromEvent(event);

	}

	@FXML protected void btnModificaCmp(MouseEvent event) {
		
		this.lblModificaOk.setVisible(false);
		nav.setVista("mostraModificaCmp", this.cmpto);
	}

	private void hideStageFromEvent(MouseEvent event){
		this.source = (Node)event.getSource(); 
		this.stage  = (Stage)this.source.getScene().getWindow();
		this.stage.hide();
	}



	@Override
	public void initializeView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("modificaCompetizione")){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				this.initializeView((CompetizioneTO) res);
				this.lblModificaOk.setVisible(true);
			}
		}else if(request.getCommandName().equals("annullaCompetizione")){
			Object res = response.getRespData();
			if(res instanceof CompetizioneTO){
				//disabilito i pane
				this.lblAnnullaOk.setVisible(true);
				this.paneVisualizzaCmp.setDisable(true);
				this.paneIscritti.setDisable(true);
			}
		}

	}


}
