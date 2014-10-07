package agroludos.presentation.views.mds;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.to.AgroludosTO;
import agroludos.to.OptionalTO;

public class ControllerMdsModificaOptional extends AgroludosController{
	private String viewName;
	private @FXML TextField txtNomeOptional;
	private @FXML ComboBox<Double> cmbPrezzoOptional;
	private @FXML ComboBox<String> cmbStatoOptional;
	private @FXML TextArea txtAreaDescrizione;
	private @FXML Label lblNomeTipoOpt;
	private OptionalTO optional;

	@Override
	public void initializeView(AgroludosTO mainTO) {
		this.optional = (OptionalTO)mainTO;
		this.txtNomeOptional.setText(this.optional.getNome());
		
		String statoOpt = this.optional.getStatoOptional().getNome();
		this.cmbStatoOptional.setValue(statoOpt);
		
		this.txtAreaDescrizione.setText(this.optional.getDescrizione());
		
		String tipoOpt = this.optional.getTipoOptional().getNome();
		this.lblNomeTipoOpt.setText(tipoOpt);
	}
	
	@FXML protected void confermaModificaOptional(MouseEvent event){
		AgroRequest request = this.getRichiesta(this.optional, "modificaOptional", this.viewName);
		AgroResponse response = respFact.createResponse();
		frontController.eseguiRichiesta(request, response);
	}

	@Override
	public void initializeView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		// TODO Auto-generated method stub

	}

}