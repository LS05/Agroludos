package agroludos.presentation.views.components.table.filter;

import agroludos.presentation.views.components.tablemodel.CmpModel;

import javafx.scene.control.TextField;

public final class TableCompetizioniFilter extends TableFilter<CmpModel> {

	@Override
	protected boolean matchesFilter(CmpModel cmpModel, TextField cmpFilterField) {
		String filterString = cmpFilterField.getText();
		boolean res = false;

		if (filterString == null || filterString.isEmpty()) {
			res = true;
		} else {
			String lowerCaseFilterString = filterString.toLowerCase();

			if (cmpModel.getNome().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (cmpModel.getCosto().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (cmpModel.getStato().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			} else if (cmpModel.getId().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			}else if (cmpModel.getMdcmail().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				res = true;
			}
		}

		return res;
	}
}