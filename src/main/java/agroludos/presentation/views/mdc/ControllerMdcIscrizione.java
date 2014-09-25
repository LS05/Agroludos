package agroludos.presentation.views.mdc;

import java.util.List;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerMdcIscrizione extends AgroludosController {

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

	@FXML private Label lblCostolblOptional1;
	@FXML private Label lblCostolblOptional2;
	@FXML private Label lblCostolblOptional3;
	@FXML private Label lblOptional1;
	@FXML private Label lblOptional2;
	@FXML private Label lblOptional3;
	@FXML private Label lblTipoOptional1;
	@FXML private Label lblTipoOptional2;
	@FXML private Label lblTipoOptional3;

	@FXML private Button btnVisualizzaCertificato;
	@FXML private Button btnAnnullaIscrizione;
	@FXML private Button btnModificaOptionalIscrizione;

	@FXML private Label lblValuta1;
	@FXML private Label lblValuta2;
	@FXML private Label lblValuta3;

	@FXML protected void btnVisualizzaCertificato(MouseEvent event) {}
	@FXML protected void btnAnnullaIscrizione(MouseEvent event) {}
	@FXML protected void btnModificaOptionalIscrizione(MouseEvent event) {}

	private String nameView;
	@FXML private Label lblEliminaIscrizioneOk;
	@FXML private GridPane paneIscrizione;
	private IscrizioneTO iscto;
	private List<OptionalTO> listOptionalScelti;
	private AgroResponse risposta;
	private AgroRequest richiesta;


	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.iscto =(IscrizioneTO) mainTO;

		this.lblCostolblOptional1.setVisible(false);
		this.lblCostolblOptional2.setVisible(false);
		this.lblCostolblOptional3.setVisible(false);
		this.lblOptional1.setVisible(false);
		this.lblOptional2.setVisible(false);
		this.lblOptional3.setVisible(false);
		this.lblTipoOptional1.setVisible(false);
		this.lblTipoOptional2.setVisible(false);
		this.lblTipoOptional3.setVisible(false);
		this.lblValuta1.setVisible(false);
		this.lblValuta2.setVisible(false);
		this.lblValuta3.setVisible(false);

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

		this.listOptionalScelti = this.iscto.getAllOptionals();

		int index=1;
		for(OptionalTO item : this.listOptionalScelti){
			if(index==1){
				this.lblCostolblOptional1.setVisible(true);
				this.lblOptional1.setVisible(true);
				this.lblTipoOptional1.setVisible(true);	
				this.lblValuta1.setVisible(true);

				this.lblCostolblOptional1.setText(item.getCosto().toString());
				this.lblOptional1.setText(item.getNome());
				this.lblTipoOptional1.setText(item.getTipoOptional().getNome());
			}else if(index==2){
				this.lblCostolblOptional2.setVisible(true);
				this.lblOptional2.setVisible(true);
				this.lblTipoOptional2.setVisible(true);	
				this.lblValuta2.setVisible(true);

				this.lblCostolblOptional2.setText(item.getCosto().toString());
				this.lblOptional2.setText(item.getNome());
				this.lblTipoOptional2.setText(item.getTipoOptional().getNome());
			}else if(index==3){
				this.lblCostolblOptional2.setVisible(true);
				this.lblOptional2.setVisible(true);
				this.lblTipoOptional2.setVisible(true);	
				this.lblValuta3.setVisible(true);

				this.lblCostolblOptional2.setText(item.getCosto().toString());
				this.lblOptional2.setText(item.getNome());
				this.lblTipoOptional2.setText(item.getTipoOptional().getNome());
			}
			index++;

		}

	}

	@FXML protected void btnAnnullaIscrizione(){
		//TODO
		System.out.println("Confermi? si...");
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta(this.iscto, "eliminaIscrizione", this.nameView);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
	}

	@Override
	public void initializeView(String nameView) {
		this.nameView = nameView;
		this.lblEliminaIscrizioneOk.setVisible(false);
		this.paneIscrizione.setDisable(true);

	}

	@Override
	protected String getNameView() {
		return this.nameView;
	}

	@Override
	protected void setNameView(String nameView) {
		this.nameView = nameView;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		if(request.getCommandName().equals("eliminaIscrizione")){
			Object res = response.getRespData();
			if(res instanceof IscrizioneTO){
				this.lblEliminaIscrizioneOk.setVisible(true);
				this.paneIscrizione.setDisable(false);
			}
		}

	}

}
