package agroludos.presentation.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import agroludos.presentation.req.AgroRequest;
import agroludos.presentation.resp.AgroResponse;
import agroludos.to.AgroludosTO;

/**
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public abstract class AgroludosController extends Controller{	

	protected Properties reqProperties;

	protected AgroludosController(){
		this.reqProperties = new Properties();
		Path reqPropPath = Paths.get("src/main/resources/properties/req.properties");
		File propertiesFile = new File(reqPropPath.toString());
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(propertiesFile);
			this.reqProperties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected AgroRequest getRichiesta(String commandName, String viewName){
		String richiesta = this.reqProperties.getProperty(commandName);
		return reqFact.createSimpleRequest(richiesta, viewName);		
	}

	protected AgroRequest getRichiesta(AgroludosTO param, String commandName, String viewName){
		String richiesta = this.reqProperties.getProperty(commandName);
		return reqFact.createDataRequest(param, richiesta, viewName);
	}

	public abstract void forward(AgroRequest request, AgroResponse response);

	public abstract void initializeView(AgroludosTO mainTO);

	public abstract void initializeView(String viewName);

	protected abstract String getNameView();
	protected abstract void setNameView(String nameView);
	protected void close(){
		nav.closeVista(getNameView());
	}
}