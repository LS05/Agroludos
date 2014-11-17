package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.views.AgroludosController;
import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.AgroludosTO;
import agroludos.to.IscrizioneTO;
import agroludos.to.PartecipanteTO;

/**
 * Gestisce la view che mostra le iscrizioni di un partecipante
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public class ControllerMdsVisualizzaIscrizioni extends AgroludosController {
	private String viewName;

	@FXML private TableView<IscModel> tableIscrizioni;
	@FXML private TableColumn<IscModel, String> iscComCol;
	@FXML private TableColumn<IscModel, String> iscDataCol;
	@FXML private TableColumn<IscModel, String> iscStatoCol;
	@FXML private Label lblNomeCognome;

	private List<IscrizioneTO> iscrizioni;

	protected IscModel iscModelRow;

	private PartecipanteTO part;

	private AgroResponse risposta;

	private AgroRequest richiesta;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof PartecipanteTO){
			this.part = (PartecipanteTO)mainTO;
			StringBuilder sb = new StringBuilder(200);
			sb.append(part.getNome());
			sb.append(" ");
			sb.append(part.getCognome());
			this.lblNomeCognome.setText(sb.toString());

			//richiesta per ottenere tutte le iscrizioni da un partecipante
			this.richiesta = this.getRichiesta(this.part,"getAllIscrizioniPartecipante", this.viewName);
			this.risposta = this.getRisposta();
			this.eseguiRichiesta(this.richiesta, this.risposta);

			this.iscComCol.setCellValueFactory(new PropertyValueFactory<IscModel, String>("competizione"));
			this.iscDataCol.setCellValueFactory(new PropertyValueFactory<IscModel, String>("data"));
			this.iscStatoCol.setCellValueFactory(new PropertyValueFactory<IscModel, String>("stato"));

			ObservableList<IscModel> res = FXCollections.observableArrayList();
			IscModel iscModel = null;

			for(IscrizioneTO iscrizione : this.iscrizioni){
				iscModel = new IscModel(iscrizione);
				res.add(iscModel);
			}

			this.tableIscrizioni.getItems().setAll(res);

			this.tableIscrizioni.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					@SuppressWarnings("unchecked")
					TableView<IscModel> table = (TableView<IscModel>) event.getSource();
					iscModelRow = table.getSelectionModel().getSelectedItem();
					if(iscModelRow != null){
						if(event.getClickCount() > 1){ 
							setVista("mostraIscrizioneMds", iscModelRow.getIscrizioneTO());
						}
					}
				}

			});
		}
	}

	@Override
	protected void initializeView(String nameView) {
		this.viewName = nameView;
	}

	@Override
	protected String getViewName() {
		return this.viewName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void forward(AgroRequest request, AgroResponse response) {
		String commandName = request.getCommandName();

		if(commandName.equals( this.getCommandName("getAllIscrizioniPartecipante") )){
			Object res = response.getRespData();
			if(res instanceof List<?>){
				List<IscrizioneTO> iscPartecipante = (List<IscrizioneTO>)res;
				this.iscrizioni = iscPartecipante;
			}
		}
	}
}