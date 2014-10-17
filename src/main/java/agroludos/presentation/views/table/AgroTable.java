package agroludos.presentation.views.table;

public interface AgroTable<T> {

	int getSelectedIndex();

	T getSelectedItem();

	void updateTable(String text);
}