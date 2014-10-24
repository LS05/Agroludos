package agroludos.presentation.views.components.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public abstract class AgroTable<T> extends TableView<T>{

	abstract int getSelectedIndex();

	abstract T getSelectedItem();

	public abstract void updateTable(String text);

	public void hideColumn(String name){
		TableColumn<T, ?> col = this.getTableColumnByName(name);
		if(col != null)
			col.setVisible(false);
	}

	private TableColumn<T, ?> getTableColumnByName(String name) {
		TableColumn<T, ?> resCol = null;
		for (TableColumn<T, ?> col : this.getColumns()){
			if (col.getText().equals(name)) 
				resCol = col;
		}
		return resCol ;
	}

	public void hideColumn(Integer index){
		TableColumn<T, ?> col = this.getTableColumnByIndex(index);
		if(col != null)
			col.setVisible(false);
	}

	private TableColumn<T, ?> getTableColumnByIndex(Integer index) {
		TableColumn<T, ?> resCol = null;
		
		try{
			resCol = this.getColumns().get(index);
		} catch(IndexOutOfBoundsException e){
			resCol = null;
		}

		return resCol;
	}
}