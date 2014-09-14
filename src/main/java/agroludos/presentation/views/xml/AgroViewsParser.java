package agroludos.presentation.views.xml;

import agroludos.exceptions.ViewNotFoundException;

public interface AgroViewsParser {
	AgroludosWindow getView(String name) throws ViewNotFoundException;
}