package agroludos.presentation.views.partecipante;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.table.TableOptional;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.AgroludosTO;
import agroludos.to.CompetizioneTO;
import agroludos.to.ErrorMessageTO;
import agroludos.to.IscrizioneTO;

/**
 * Gestisce la view di una competizione vista da un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerPartCompetizione extends AgroludosController{

	private String viewName;

	private CompetizioneTO cmpto;

	@FXML private GridPane paneVisualizzaCmp;
	@FXML private GridPane paneIscritti;
	@FXML private GridPane paneTableOptional;
	private TableOptional tableOptional;

	//tabella iscrizioni alla competizione
	@FXML private TableView<IscModel> tblIscritti;
	@FXML private TableColumn<IscModel, String> iscNomeCol;
	@FXML private TableColumn<IscModel, String> iscCognomeCol;
	@FXML private TableColumn<IscModel, String> iscEmailCol;
	private ObservableList<IscModel> listaTabIsc;
	private List<IscrizioneTO> listIsc;

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
	@FXML private Button btnIscriviti;

	private IscrizioneTO mainIscr;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	public void initializeView(String nameView) {
		this.viewName = nameView;
	}

	@Override
	public void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof CompetizioneTO){
			this.cmpto = (CompetizioneTO) mainTO;
			this.mainIscr = toFact.createIscrizioneTO();

			this.lblNomeCompetizione.setText(this.cmpto.getNome());
			this.lblData.setText(this.cmpto.getData().toString());
			this.lblNmin.setText(Integer.toString(this.cmpto.getNmin()));
			this.lblNmax.setText(Integer.toString(this.cmpto.getNmax()));
			this.lblCosto.setText(Double.toString(this.cmpto.getCosto()));
			this.lblTipo.setText(this.cmpto.getTipoCompetizione().getNome());

			//richiesta per ottenere le iscrizioni attive di questa competizione
			this.richiesta = this.getRichiesta(this.cmpto, "getAllIscrizioniAttiveByCmp", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

			this.lblNiscritti.setText(Integer.toString(this.listIsc.size()));
			this.lblStato.setText(this.cmpto.getStatoCompetizione().getNome());
			this.txtDescrizione.setText(this.cmpto.getDescrizione());
			this.btnIscriviti.setDisable(false);



			this.listaTabIsc = this.getListTabellaIsc();
			this.initIscTable();

			this.tableOptional = new TableOptional();
			this.paneTableOptional.getChildren().add(this.tableOptional);
			this.paneTableOptional.setVisible(true);
			this.tableOptional.setAll(this.cmpto.getAllOptionals());

			this.tableOptional.hideColumn(4);
			this.tableOptional.hideColumn(1);
		}
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	private ObservableList<IscModel> getListTabellaIsc(){
		ObservableList<IscModel> res = FXCollections.observableArrayList();
		IscModel modelIsc = null;

		for(IscrizioneTO isc : this.listIsc){
			modelIsc = new IscModel(isc);
			res.add(modelIsc);
		}

		return res;
	}

	private <S,T> TableColumn<S, T> initColumn(TableColumn<S, T> col, String colName){
		col.setCellValueFactory(new PropertyValueFactory<S, T>(colName));
		return col;
	}

	private void initIscTable(){
		this.initColumn(this.iscNomeCol, "nome");
		this.initColumn(this.iscCognomeCol, "cognome");
		this.initColumn(this.iscEmailCol, "email");

		this.tblIscritti.getItems().setAll(this.listaTabIsc);
	}

	/**
	 * mostra la view per l'iscrizione alla competizione
	 * @param event
	 */
	@FXML protected void btnIscrivitiClicked(MouseEvent event){
		this.mainIscr.setCompetizione(this.cmpto);
		this.setVista("mostraIscrPart", this.mainIscr);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if( commandName.equals( this.getCommandName("inserisciIscrizione") )){
			Object res = response.getRespData();
			if(res instanceof String){
				initializeView(this.cmpto);
				ErrorMessageTO msg = toFact.createErrMessageTO();
				msg.setMessage((String) res);
				this.setVista("messageDialog", msg);

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