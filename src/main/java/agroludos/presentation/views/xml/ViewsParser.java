package agroludos.presentation.views.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import agroludos.exceptions.ViewNotFoundException;
import agroludos.utility.xml.AgroParser;

class ViewsParser extends AgroParser implements AgroViewsParser{
	private Map<String, AgroludosWindow> views;
	private AgroViews parsedViews = null;

	ViewsParser() throws JAXBException{
		super(AgroViews.class, "src/main/java/agroludos/presentation/views/xml/views.xml");
		this.parsedViews = (AgroViews) this.parseRes;
		this.views = new HashMap<String, AgroludosWindow>();

		List<View> listView = parsedViews.getViews().getView();

		for(View e : listView){

			if("view".equals(e.getTipo())){
				this.views.put(e.getName(), new AgroludosView(e));
			} else if("dialog".equals(e.getTipo())){
				this.views.put(e.getName(), new AgroludosDialog(e));
			}

		}
	}

	public AgroludosWindow getView(String viewName) throws ViewNotFoundException{
		AgroludosWindow view = null;
		if(this.views.containsKey(viewName)){
			view = this.views.get(viewName);
		} else {
			throw new ViewNotFoundException("Vista: " + " \"" + viewName + " \"" +  "non trovata! Controllare che il nome della vista sia presente nel file xml views.xml in agroludos.presentation.views.xml");
		}
		return view;
	}
}