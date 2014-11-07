package agroludos.presentation.views.components.table.filter;

import agroludos.presentation.views.components.table.AgroTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

abstract class TableFilter<T> {

	private ObservableList<T> masterData;
	private ObservableList<T> filteredData;

	TableFilter(){
		this.filteredData = FXCollections.observableArrayList();
		this.masterData = FXCollections.observableArrayList();
	}

	public void setData(AgroTable<T> mainTable){
		if(mainTable.getItems().size() == 0){
			this.masterData.clear();
		} else {
			for(T m : mainTable.getItems()){
				this.masterData.add(m);
			}
		}
	}

	/**
	 * Updates the this.filteredData to contain all data from the masterData that
	 * matches the current filter.
	 */
	public void updateFilteredData(AgroTable<T> mainTable, TextField cmpFilterField) {
		this.filteredData.clear();

		for (T p : this.masterData) {
			if (matchesFilter(p, cmpFilterField)) {
				this.filteredData.add(p);
			}
		}

		mainTable.getItems().clear();
		mainTable.getItems().setAll(this.filteredData);
	}

	public void resetResearch(AgroTable<T> mainTable, TextField filterField){
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