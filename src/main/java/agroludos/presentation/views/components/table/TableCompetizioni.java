package agroludos.presentation.views.components.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import agroludos.presentation.views.components.tablemodel.CmpModel;
import agroludos.to.CompetizioneTO;

public class TableCompetizioni extends AgroTable<CmpModel>{
	private TableColumn<CmpModel, String> cmpColId;
	private TableColumn<CmpModel, String> cmpColNome;
	private TableColumn<CmpModel, String> cmpColDesc;
	private TableColumn<CmpModel, String> cmpColCosto;
	private TableColumn<CmpModel, String> cmpColStato;
	private TableColumn<CmpModel, String> cmpColIscritti;
	private TableColumn<CmpModel, String> cmpColData;
	private TableColumn<CmpModel, String> cmpColMdcMail;

	public TableCompetizioni(){
		this.cmpColId = new TableColumn<CmpModel, String>("Id");
		this.cmpColNome = new TableColumn<CmpModel, String>("Nome");
		this.cmpColDesc = new TableColumn<CmpModel, String>("Descrizione");
		this.cmpColCosto = new TableColumn<CmpModel, String>("Costo");
		this.cmpColStato = new TableColumn<CmpModel, String>("Stato");
		this.cmpColIscritti = new TableColumn<CmpModel, String>("N.iscritti");
		this.cmpColData = new TableColumn<CmpModel, String>("Data");
		this.cmpColMdcMail = new TableColumn<CmpModel, String>("Emai Manager");

		this.cmpColId.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("id"));
		this.cmpColNome.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("nome"));
		this.cmpColDesc.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("descrizione"));
		this.cmpColCosto.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("costo"));
		this.cmpColStato.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("stato"));
		this.cmpColIscritti.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("iscritti"));
		this.cmpColData.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("data"));
		this.cmpColMdcMail.setCellValueFactory(new PropertyValueFactory<CmpModel, String>("mdcmail"));
		
		this.getColumns().add(this.cmpColId);
		this.getColumns().add(this.cmpColNome);
		this.getColumns().add(this.cmpColDesc);
		this.getColumns().add(this.cmpColCosto);
		this.getColumns().add(this.cmpColStato);
		this.getColumns().add(this.cmpColIscritti);
		this.getColumns().add(this.cmpColData);
		this.getColumns().add(this.cmpColMdcMail);
		
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
		CmpModel cmpModel = null;

		for(CompetizioneTO comp : listComp){
			cmpModel = new CmpModel(comp);
			res.add(cmpModel);
		}

		getItems().setAll(res);

	}
}