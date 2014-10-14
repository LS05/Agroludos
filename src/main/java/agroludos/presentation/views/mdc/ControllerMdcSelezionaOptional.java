package agroludos.presentation.views.mdc;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.tablemodel.OptModel;
import agroludos.presentation.views.table.TableOptional;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.OptionalTO;
import agroludos.to.TipiAgroludosTO;
import agroludos.to.TipoOptionalTO;

public class ControllerMdcSelezionaOptional extends AgroludosController{

	private String viewName;
	@FXML private Label lblTipoOptional;
	@FXML private Label lblPassi;

	//Tabella Optional
	private TableOptional tableOptional;
	@FXML private TableView<OptModel> tableOptionals;
	@FXML private TableColumn<OptModel, String> clmNomeOptional;
	@FXML private TableColumn<OptModel, String> clmDescrizioneOptional;
	@FXML private TableColumn<OptModel, String> clmPrezzoOptional;

	@FXML private Button btnSalta;
	@FXML private Button btnIndietro;
	@FXML private Button btnAvanti;
	@FXML private Button btnConferma;

	private CompetizioneTO cmpto;
	private Integer nPassi;
	private AgroResponse risposta;
	private AgroRequest richiesta;
	private List<TipiAgroludosTO> listTipiOpt;
	private int passoCorrente;
	private List<OptionalTO> listOpt;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		this.cmpto = (CompetizioneTO) mainTO;
		this.tableOptional = new TableOptional();
		
		this.risposta = respFact.createResponse();
		this.richiesta = this.getRichiesta("getAllTipoOptional", this.viewName);
		frontController.eseguiRichiesta(this.richiesta, this.risposta);
		
		this.setLabelPassi();
		this.btnAvanti.setVisible(true);
		this.btnConferma.setVisible(false);
		this.btnIndietro.setVisible(true);
		this.btnIndietro.setVisible(true);
		this.lblTipoOptional.setText(this.listTipiOpt.get(this.passoCorrente-1).getNome());
		
		this.setTable((TipoOptionalTO) this.listTipiOpt.get(this.passoCorrente-1));
	}

	private void setLabelPassi(){
		this.lblPassi.setText("Passo " + this.passoCorrente + " di " + this.nPassi);
	}
	
	private void setTable(TipoOptionalTO tipoOpt){
		this.tableOptional.setAll(tipoOpt.getAllOptionalAttivi());
	}
	
	
	@Override
	protected void initializeView(String viewName) {
		this.viewName = viewName;

	}


	@Override
	public String getViewName() {
		return this.viewName;
	}

	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.reqProperties.getProperty("getAllTipoOptional") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				List<TipiAgroludosTO> tipiOptList = (List<TipiAgroludosTO>)res;
				this.listTipiOpt = tipiOptList;
				this.nPassi = this.listTipiOpt.size();
				this.passoCorrente = 1;
			}
		}

	}
}