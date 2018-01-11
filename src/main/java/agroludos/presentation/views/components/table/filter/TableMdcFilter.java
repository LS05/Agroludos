package agroludos.presentation.views.components.table.filter;

import javafx.scene.control.TextField;

import agroludos.presentation.views.components.tablemodel.MdcModel;

public class TableMdcFilter extends TableFilter<MdcModel>{

	@Override
	protected boolean matchesFilter(MdcModel mdcModel, TextField mdcFilterField) {
		String filterString = mdcFilterField.getText();
		boolean res = false;

		if (filterString == null || filterString.isEmpty()) {
			res = true;
		} else {
			String lowerCaseFilterString = filterString.toLowerCase();

			if (mdcModel.getNome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (mdcModel.getCognome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (mdcModel.getEmail().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			}
		}

		return res;
	}

}