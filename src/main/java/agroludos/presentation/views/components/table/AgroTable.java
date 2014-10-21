package agroludos.presentation.views.components.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public abstract class AgroTable<T> extends TableView<T>{

	abstract int getSelectedIndex();

	abstract T getSelectedItem();

	abstract void updateTable(String text);
	
	public void hideColumn(String name){
		this.getTableColumnByName(name).setVisible(false);
	}
	
	private TableColumn<T, ?> getTableColumnByName(String name) {
		TableColumn<T, ?> resCol=null;
		for (TableColumn<T, ?> col : this.getColumns())
	        if (col.getText().equals(name)) resCol = col ;
	    return resCol ;
	}
	
}