package agroludos.system;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

class SystemConfImpl implements SystemConf {
	private CompositeConfiguration config; 
	private PropertiesConfiguration fileConf;

	SystemConfImpl() throws ConfigurationException{
		this.config = new CompositeConfiguration();

		this.config.addConfiguration(new SystemConfiguration());
		this.config.addConfiguration(new PropertiesConfiguration("properties/main.properties"));
		
		this.fileConf = new PropertiesConfiguration("properties/filesystem.properties");
		this.config.addConfiguration(this.fileConf);
	}

	public void setTipoDB(String tipoDB){
		this.config.setProperty("db", tipoDB);
	}

	public String getTipoDB(){
		return this.config.getString("db");
	}

	public String getTipoConf(){
		return this.config.getString("configurazione");
	}

	public String getTipoCert(){
		return this.config.getString("certificato");
	}
}