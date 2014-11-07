package agroludos.presentation.views.components.table.filter;

import javafx.scene.control.TextField;
import agroludos.presentation.views.components.tablemodel.PartModel;

public final class TablePartecipantiFilter extends TableFilter<PartModel>{

	@Override
	protected boolean matchesFilter(PartModel partModel, TextField cmpFilterField) {
		String filterString = cmpFilterField.getText();
		if (filterString == null || filterString.isEmpty()) {
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if (partModel.getNome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (partModel.getCognome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (partModel.getEmail().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (partModel.getUsername().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false;
	}
}
