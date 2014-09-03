package agroludos.presentation.views.xml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

class ViewsParser implements AgroViewsParser{
	private Map<String, AgroludosWindow> views;
	private AgroViews agView = null;
	
	ViewsParser(){
		this.views = new HashMap<String, AgroludosWindow>();
		this.setViews();
		
		List<View> listView = agView.getViews().getView();
		
		for(View e : listView){
			AgroludosWindow agWindow = null;
			
			if(e.getTipo().equals("view")){
				agWindow = new AgroludosView(e);
			} else if(e.getTipo().equals("dialog")){
				agWindow = new AgroludosDialog(e);
			}
			
			this.views.put(e.getName(), agWindow);
		}
	}
	
	void setViews(){
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(AgroViews.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			// specify the location and name of xml file to be read
			File XMLfile = new File("src/main/java/agroludos/presentation/views/xml/views.xml");

			// this will create Java object - country from the XML file
			this.agView = (AgroViews) jaxbUnmarshaller.unmarshal(XMLfile);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public AgroludosWindow getView(String name){
		return this.views.get(name);
	}
}