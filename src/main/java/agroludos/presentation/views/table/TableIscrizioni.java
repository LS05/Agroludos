package agroludos.presentation.views.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.IscrizioneTO;

public class TableIscrizioni extends TableView<IscModel> implements AgroTable<IscModel>{
	private TableColumn<IscModel, String> iscColNome;
	private TableColumn<IscModel, String> iscColData;
	private TableColumn<IscModel, String> iscColTipo;
	private TableColumn<IscModel, String> iscColMan;
	private TableColumn<IscModel, String> iscColStato;

	public TableIscrizioni(){
		this.iscColNome = new TableColumn<IscModel, String>("nome");
		this.iscColData = new TableColumn<IscModel, String>("data");
		this.iscColTipo = new TableColumn<IscModel, String>("tipo");
		this.iscColMan = new TableColumn<IscModel, String>("manager");
		this.iscColStato = new TableColumn<IscModel, String>("stato");
		

		this.iscColNome.setCellValueFactory(new PropertyValueFactory<IscModel, String>("nome"));
		this.iscColData.setCellValueFactory(new PropertyValueFactory<IscModel, String>("data"));
		this.iscColTipo.setCellValueFactory(new PropertyValueFactory<IscModel, String>("tipo"));
		this.iscColMan.setCellValueFactory(new PropertyValueFactory<IscModel, String>("manager"));
		this.iscColStato.setCellValueFactory(new PropertyValueFactory<IscModel, String>("stato"));

		this.getColumns().add(this.iscColNome);
		this.getColumns().add(this.iscColData);
		this.getColumns().add(this.iscColTipo);
		this.getColumns().add(this.iscColStato);
		
		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);
	}

	@Override
	public int getSelectedIndex() {
		return this.getSelectedIndex();
	}

	@Override
	public IscModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}

	public void setAll(List<IscrizioneTO> listIscr){

		ObservableList<IscModel> res = FXCollections.observableArrayList();
		IscModel partModel = null;

		for(IscrizioneTO comp : listIscr){
			partModel = new IscModel(comp);
			res.add(partModel);
		}

		getItems().setAll(res);

	}
}