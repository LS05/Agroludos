package agroludos.presentation.views.xml;

import agroludos.exceptions.system.ViewNotFoundException;

public interface AgroViewsParser {
	AgroludosWindow getView(String name) throws ViewNotFoundException;
}