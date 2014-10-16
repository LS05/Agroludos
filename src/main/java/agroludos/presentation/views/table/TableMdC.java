package agroludos.presentation.views.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import agroludos.presentation.views.components.tablemodel.MdcModel;
import agroludos.to.ManagerDiCompetizioneTO;

public class TableMdC extends TableView<MdcModel> implements AgroTable<MdcModel>{
	private TableColumn<MdcModel, String> mdcNomeCol;
	private TableColumn<MdcModel, String> mdcCognomeCol;
	private TableColumn<MdcModel, String> mdcEmailCol;

	public TableMdC(List<ManagerDiCompetizioneTO> listComp){

		this.mdcNomeCol = new TableColumn<MdcModel, String>();
		this.mdcCognomeCol = new TableColumn<MdcModel, String>();
		this.mdcEmailCol = new TableColumn<MdcModel, String>();

		this.mdcNomeCol.setCellValueFactory(new PropertyValueFactory<MdcModel, String>("nome"));
		this.mdcCognomeCol.setCellValueFactory(new PropertyValueFactory<MdcModel, String>("cognome"));
		this.mdcEmailCol.setCellValueFactory(new PropertyValueFactory<MdcModel, String>("email"));

		this.mdcNomeCol.setText("Nome");
		this.mdcCognomeCol.setText("Cognome");
		this.mdcEmailCol.setText("Email");

		this.setColumnResizePolicy(CONSTRAINED_RESIZE_POLICY);

		this.getColumns().add(this.mdcNomeCol);
		this.getColumns().add(this.mdcCognomeCol);
		this.getColumns().add(this.mdcEmailCol);

		ObservableList<MdcModel> res = FXCollections.observableArrayList();
		MdcModel partModel = null;

		for(ManagerDiCompetizioneTO manager : listComp){
			partModel = new MdcModel(manager);
			res.add(partModel);
		}

		getItems().setAll(res);
	}

	@Override
	public int getSelectedIndex() {
		return this.getSelectionModel().getSelectedIndex();
	}

	@Override
	public MdcModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}
	
	public void addItem(ManagerDiCompetizioneTO mdc){
		MdcModel mdcMod = new MdcModel(mdc);
		this.getItems().add(mdcMod);
	}
}