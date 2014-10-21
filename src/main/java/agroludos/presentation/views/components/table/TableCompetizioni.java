package agroludos.presentation.views.components.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.to.CompetizioneTO;

public class TableCompetizioni extends AgroTable<CmpModel>{
	private TableColumn<CmpModel, String> cmpColNome;
	private TableColumn<CmpModel, String> cmpColDesc;
	private TableColumn<CmpModel, String> cmpColCosto;
	private TableColumn<CmpModel, String> cmpColStato;

	public TableCompetizioni(){
		this.cmpColNome = new TableColumn<CmpModel, String>("nome");
		this.cmpColDesc = new TableColumn<CmpModel, String>("descrizione");
		this.cmpColCosto = new TableColumn<CmpModel, String>("costo");
		this.cmpColStato = new TableColumn<CmpModel, String>("stato");

		this.cmpColNome.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("nome"));
		this.cmpColDesc.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("descrizione"));
		this.cmpColCosto.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("costo"));
		this.cmpColStato.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("stato"));

		this.getColumns().add(this.cmpColNome);
		this.getColumns().add(this.cmpColDesc);
		this.getColumns().add(this.cmpColCosto);
		this.getColumns().add(this.cmpColStato);
		
		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
	}

	@Override
	public int getSelectedIndex() {
		return this.getSelectedIndex();
	}

	@Override
	public CmpModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}

	public void setAll(List<CompetizioneTO> listComp){

		ObservableList<CmpModel> res = FXCollections.observableArrayList();
		CmpModel partModel = null;

		for(CompetizioneTO comp : listComp){
			partModel = new CmpModel(comp);
			res.add(partModel);
		}

		getItems().setAll(res);

	}

	@Override
	public void updateTable(String text) {
		// TODO Auto-generated method stub
		
	}
}