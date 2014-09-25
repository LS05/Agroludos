package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import agroludos.presentation.views.tablemodel.CmpModel;
import agroludos.to.CompetizioneTO;

public class TableCompetizioni extends TableView<CmpModel> implements AgroTable<CmpModel>{
	private TableColumn<CmpModel, String> cmpColNome;
	private TableColumn<CmpModel, String> cmpColDesc;
	private TableColumn<CmpModel, String> cmpColCosto;
	private TableColumn<CmpModel, String> cmpColStato;

	TableCompetizioni(List<CompetizioneTO> listComp){
		this.cmpColNome.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("nome"));
		this.cmpColDesc.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("descrizione"));
		this.cmpColCosto.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("costo"));
		this.cmpColStato.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("stato"));

		ObservableList<CmpModel> res = FXCollections.observableArrayList();
		CmpModel partModel = null;

		for(CompetizioneTO comp : listComp){
			partModel = new CmpModel(comp);
			res.add(partModel);
		}

		getItems().setAll(res);
	}
	
	@Override
	public int getSelectedIndex() {
		return this.getSelectedIndex();
	}

	@Override
	public CmpModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}
}
