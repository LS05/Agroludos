package agroludos.presentation.views;

import agroludos.presentation.views.xml.AgroViewsParser;
import agroludos.presentation.views.xml.AgroludosWindow;

class ViewsLoader{
	private AgroViewsParser parser;
	
	ViewsLoader(AgroViewsParser parser){
		this.parser = parser;
	}
	
	AgroludosWindow getView(String view){
		return this.parser.getView(view);
	}
	
	AgroludosWindow getDialog(String dialog){
		return this.parser.getDialog(dialog);
	}
}