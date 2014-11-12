package agroludos.presentation.views.components.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import agroludos.presentation.views.components.tablemodel.IscModel;
import agroludos.to.IscrizioneTO;

public class TableIscrizioni extends AgroTable<IscModel>{
	private TableColumn<IscModel, String> iscColNomeCompetizione;
	private TableColumn<IscModel, String> iscColDataCmp;
	private TableColumn<IscModel, String> iscColTipo;
	private TableColumn<IscModel, String> iscColMan;
	private TableColumn<IscModel, String> iscColStato;

	public TableIscrizioni(){
		this.iscColNomeCompetizione = new TableColumn<IscModel, String>("Nome competizione");
		this.iscColDataCmp = new TableColumn<IscModel, String>("Data Competizione");
		this.iscColTipo = new TableColumn<IscModel, String>("Tipo");
		this.iscColMan = new TableColumn<IscModel, String>("Manager");
		this.iscColStato = new TableColumn<IscModel, String>("Stato");


		this.iscColNomeCompetizione.setCellValueFactory(new PropertyValueFactory<IscModel, String>("competizione"));
		this.iscColDataCmp.setCellValueFactory(new PropertyValueFactory<IscModel, String>("datacmp"));
		this.iscColTipo.setCellValueFactory(new PropertyValueFactory<IscModel, String>("tipo"));
		this.iscColMan.setCellValueFactory(new PropertyValueFactory<IscModel, String>("manager"));
		this.iscColStato.setCellValueFactory(new PropertyValueFactory<IscModel, String>("stato"));

		this.getColumns().add(this.iscColNomeCompetizione);
		this.getColumns().add(this.iscColDataCmp);
		this.getColumns().add(this.iscColTipo);
		this.getColumns().add(this.iscColMan);
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