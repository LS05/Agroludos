package agroludos.presentation.views.components.table.filter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

abstract class TableFilter<T> {

	private ObservableList<T> masterData;
	private ObservableList<T> filteredData;

	protected TableFilter(){
		this.filteredData = FXCollections.observableArrayList();
		this.masterData = FXCollections.observableArrayList();
	}

	public void setData(TableView<T> mainTable){
		this.masterData.clear();
		for(T m : mainTable.getItems()){
			this.masterData.add(m);
		}
	}

	/**
	 * Updates the this.filteredData to contain all data from the masterData that
	 * matches the current filter.
	 */
	public void updateFilteredData(TableView<T> mainTable, TextField cmpFilterField) {
		this.filteredData.clear();

		for (T p : this.masterData) {
			if (matchesFilter(p, cmpFilterField)) {
				this.filteredData.add(p);
			}
		}

		mainTable.getItems().clear();
		mainTable.getItems().setAll(this.filteredData);
	}

	public void resetResearch(TableView<T> mainTable, TextField filterField){
		if(mainTable != null && filterField != null){
			filterField.setText("");
			mainTable.getItems().setAll(this.masterData);
		}
	}

	/**
	 * Returns true if the T matches the current filter. Lower/Upper case
	 * is ignored.
	 * 
	 * @param tableModel
	 * @return
	 */
	protected abstract boolean matchesFilter(T tableModel, TextField cmpFilterField);

}