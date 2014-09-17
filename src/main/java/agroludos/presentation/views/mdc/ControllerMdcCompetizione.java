package agroludos.presentation.views.mdc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;

public class ControllerMdcCompetizione extends AgroludosController {

	private CompetizioneTO cmpto;
	
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

	@Override
	public void initializeView(AgroludosTO mainTO) {
		cmpto=(CompetizioneTO) mainTO;
		this.lblNomeCompetizione.setText(this.cmpto.getNome());
		this.lblData.setText(this.cmpto.getData().toString());
		this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
		this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
		this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
		this.lblTipo.setText(this.cmpto.getNomeTipo());
		this.lblNiscritti.setText(Integer.toString(this.cmpto.getAllIscritti().size()));
		this.lblStato.setText(this.cmpto.getNomeStato());
		this.txtDescrizione.setText(this.cmpto.getDescrizione());
		
	}

	@Override
	public void initializeView() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub
		
	}


}
