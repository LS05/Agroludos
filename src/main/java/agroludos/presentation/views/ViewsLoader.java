package agroludos.presentation.views;

import agroludos.presentation.views.xml.AgroludosWindow;
import agroludos.presentation.views.xml.ViewsParser;

class ViewsLoader{
	private ViewsParser test;
	
	ViewsLoader(){
		this.test = new ViewsParser();
	}
	
	AgroludosWindow getView(String view){
		return this.test.getView(view);
	}
	
	AgroludosWindow getDialog(String dialog){
		return this.test.getDialog(dialog);
	}
}