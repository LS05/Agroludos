package agroludos.presentation.views.components.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public abstract class AgroTable<T> extends TableView<T>{

	public void hideColumn(Integer index){
		TableColumn<T, ?> col = this.getTableColumnByIndex(index);
		if(col != null){
			col.setVisible(false);
		}
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

	abstract int getSelectedIndex();

	abstract T getSelectedItem();

}