package agroludos.presentation.views.mds;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import agroludos.presentation.views.tablemodel.OptModel;
import agroludos.to.OptionalTO;

class TableOptional extends TableView<OptModel> implements AgroTable<OptModel>{
	private TableColumn<OptModel, String> optColNome;
	private TableColumn<OptModel, String> optColDesc;
	private TableColumn<OptModel, String> optColPrezzo;
	private TableColumn<OptModel, String> optColStato;

	TableOptional(){
		this.optColNome = new TableColumn<OptModel, String>("nome");
		this.optColDesc = new TableColumn<OptModel, String>("descrizione");
		this.optColPrezzo = new TableColumn<OptModel, String>("costo");
		this.optColStato = new TableColumn<OptModel, String>("nomeStato");

		this.optColNome.setCellValueFactory(new PropertyValueFactory<OptModel, String>("nome"));
		this.optColDesc.setCellValueFactory(new PropertyValueFactory<OptModel, String>("descrizione"));
		this.optColPrezzo.setCellValueFactory(new PropertyValueFactory<OptModel, String>("costo"));
		this.optColStato.setCellValueFactory(new PropertyValueFactory<OptModel, String>("nomeStato"));
		this.optColStato.setCellFactory(new Callback<TableColumn<OptModel, String>, TableCell<OptModel, String>>() {
			@Override public TableCell<OptModel, String> call(TableColumn<OptModel, String> statoColumn) {
				return new TableCell<OptModel, String>() {
					@Override public void updateItem(final String item, final boolean empty) {
						super.updateItem(item, empty);

						// clear any custom styles

						this.getStyleClass().remove("disattivoCell");
						this.getStyleClass().remove("attivoCell");
						this.getTableRow().getStyleClass().remove("optionalRow");

						// update the item and set a custom style if necessary
						if (item != null) {
							setText(item);
							int index = this.getIndex();
							OptModel optional = this.getTableView().getItems().get(index);
							this.getStyleClass().add(optional.getStato() == 0 ? "disattivoCell" : "attivoCell");
							this.getTableRow().getStyleClass().add("optionalRow");
						}
					}
				};
			}
		});
	}

	public void setAll(List<OptionalTO> listComp){
		ObservableList<OptModel> res = FXCollections.observableArrayList();
		OptModel partModel = null;

		for(OptionalTO optional : listComp){
			partModel = new OptModel(optional);
			res.add(partModel);
		}

		getItems().setAll(res);
	}

	@Override
	public int getSelectedIndex() {
		return this.getSelectedIndex();
	}

	@Override
	public OptModel getSelectedItem() {
		return this.getSelectionModel().getSelectedItem();
	}
}
