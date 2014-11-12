package agroludos.presentation.views.components.table.filter;

import javafx.scene.control.TextField;
import agroludos.presentation.views.components.tablemodel.OptModel;

public class TableOptionalFilter extends TableFilter<OptModel>{

	@Override
	protected boolean matchesFilter(OptModel tableModel, TextField cmpFilterField) {
		String filterString = cmpFilterField.getText();
		boolean res = false;

		if (filterString == null || filterString.isEmpty()) {
			res = true;
		} else {
			String lowerCaseFilterString = filterString.toLowerCase();

			if (tableModel.getNome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (tableModel.getDescrizione().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (tableModel.getCosto().toString().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (tableModel.getNomeStato().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			}
		}

		return res;
	}

}