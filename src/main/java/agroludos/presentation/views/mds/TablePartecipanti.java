package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import agroludos.presentation.views.tablemodel.PartModel;
import agroludos.to.PartecipanteTO;

public class TablePartecipanti extends TableView<PartModel> implements AgroTable<PartModel>{
	private TableColumn<PartModel, String> partColNome;
	private TableColumn<PartModel, String> partColCognome;
	private TableColumn<PartModel, String> partColEmail;
	private TableColumn<PartModel, String> partColUsername;

	TablePartecipanti(List<PartecipanteTO> listComp){
		this.partColNome.setCellValueFactory(new PropertyValueFactory<PartModel, String>("nome"));
		this.partColCognome.setCellValueFactory(new PropertyValueFactory<PartModel, String>("cognome"));
		this.partColEmail.setCellValueFactory(new PropertyValueFactory<PartModel, String>("email"));
		this.partColUsername.setCellValueFactory(new PropertyValueFactory<PartModel, String>("username"));

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
		return this.getSelectedIndex();
	}

	@Override
	public PartModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}
}