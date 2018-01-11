package agroludos.presentation.views;

import agroludos.exceptions.system.ViewNotFoundException;
import agroludos.presentation.views.xml.AgroViewsParser;
import agroludos.presentation.views.xml.AgroludosWindow;

/**
 * Utilizza il metodo getView di {@link AgroViewsParser} per restuire una {@link AgroludosWindow} 
 * che descrive una view la quale può essere un dialog, una mainview o una view di controllo
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
class ViewsLoader{
	private AgroViewsParser parser;
	
	ViewsLoader(AgroViewsParser parser){
		this.parser = parser;
	}
	
	/**
	 * Restituisce un {@link AgroludosWindow}
	 * @param view
	 * @return
	 * @throws ViewNotFoundException
	 */
	AgroludosWindow getView(String view) throws ViewNotFoundException{
		return this.parser.getView(view);
	}
}