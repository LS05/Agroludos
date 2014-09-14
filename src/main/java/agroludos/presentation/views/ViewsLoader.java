package agroludos.presentation.views;

import agroludos.exceptions.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroViewsParser;
import agroludos.presentation.views.xml.AgroludosWindow;

class ViewsLoader{
	private AgroViewsParser parser;
	
	ViewsLoader(AgroViewsParser parser){
		this.parser = parser;
	}
	
	AgroludosWindow getView(String view) throws ViewNotFoundException{
		return this.parser.getView(view);
	}
}