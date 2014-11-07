package agroludos.presentation.views.components.table.filter;

import javafx.scene.control.TextField;
import agroludos.presentation.views.components.tablemodel.MdcModel;

public class TableMdcFilter extends TableFilter<MdcModel>{

	@Override
	protected boolean matchesFilter(MdcModel mdcModel, TextField mdcFilterField) {
		String filterString = mdcFilterField.getText();

		if (filterString == null || filterString.isEmpty()) {
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if (mdcModel.getNome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (mdcModel.getCognome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		} else if (mdcModel.getEmail().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false;
	}

}