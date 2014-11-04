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

public class ControllerMdsVisualizzaIscrizioni extends AgroludosController {
	private String viewName;
	
	@FXML private TableView<IscModel> tableIscrizioni;
	@FXML private TableColumn<IscModel, String> iscComCol;
	@FXML private TableColumn<IscModel, String> iscDataCol;
	@FXML private TableColumn<IscModel, String> iscStatoCol;
	@FXML private Label lblNomeCognome;
	
	private List<IscrizioneTO> iscrizioni;

	protected IscModel iscModelRow;

	@Override
	protected void initializeView(AgroludosTO mainTO) {
		if(mainTO instanceof PartecipanteTO){
			PartecipanteTO part = (PartecipanteTO)mainTO;
			StringBuilder sb = new StringBuilder();
			sb.append(part.getNome());
			sb.append(" ");
			sb.append(part.getCognome());
			this.lblNomeCognome.setText(sb.toString());
			this.iscrizioni = part.getAllIscrizioni();
			
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
						if (event.getClickCount() > 1) 
							setVista("mostraIscrizioneMds", iscModelRow.getIscrizioneTO());
					}
				}

			});
		}
	}

	@Override
	protected void initializeView(String viewName) {
		// TODO Auto-generated method stub
		
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