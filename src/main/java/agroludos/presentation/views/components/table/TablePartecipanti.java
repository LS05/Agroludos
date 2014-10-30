package agroludos.presentation.views.components.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import agroludos.presentation.views.components.tablemodel.PartModel;
import agroludos.to.PartecipanteTO;

public class TablePartecipanti extends AgroTable<PartModel>{
	private TableColumn<PartModel, String> partColNome;
	private TableColumn<PartModel, String> partColCognome;
	private TableColumn<PartModel, String> partColEmail;
	private TableColumn<PartModel, String> partColUsername;

	public TablePartecipanti(List<PartecipanteTO> listComp){
		this.partColNome = new TableColumn<PartModel, String>("nome");
		this.partColCognome = new TableColumn<PartModel, String>("cognome");
		this.partColEmail = new TableColumn<PartModel, String>("email");
		this.partColUsername = new TableColumn<PartModel, String>("username");

		this.partColNome.setCellValueFactory(new PropertyValueFactory<PartModel, String>("nome"));
		this.partColCognome.setCellValueFactory(new PropertyValueFactory<PartModel, String>("cognome"));
		this.partColEmail.setCellValueFactory(new PropertyValueFactory<PartModel, String>("email"));
		this.partColUsername.setCellValueFactory(new PropertyValueFactory<PartModel, String>("username"));

		this.getColumns().add(this.partColNome);
		this.getColumns().add(this.partColCognome);
		this.getColumns().add(this.partColEmail);
		this.getColumns().add(this.partColUsername);
		
		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

		ObservableList<PartModel> res = FXCollections.observableArrayList();
		PartModel partModel = null;

		for(PartecipanteTO partecipante : listComp){
			partModel = new PartModel(partecipante);
			res.add(partModel);
		}

		getItems().setAll(res);
	}

	@Override
	public int getSelectedIndex() {
		return this.getSelectionModel().getSelectedIndex();
	}

	@Override
	public PartModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}

	@Override
	public void updateTable(String text) {
		// TODO Auto-generated method stub
		
	}
}