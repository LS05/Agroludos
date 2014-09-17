package agroludos.integration.dao.file.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class XmlData {
	
	private String hibDriver;
	private String hibUsername;
	private String hibPassword;
	private String hibUrl;
	private String hibDialect;
	
	private String confPath;
	private String driver;
	private String dialect;
	private String url;

	XmlData() throws IOException{
		try {
			Properties prop = new Properties();
			InputStream inputStream = new FileInputStream("src/main/resources/properties/config.properties");
			prop.load(inputStream);
			hibDriver = prop.getProperty("hibDriver");
			hibUsername = prop.getProperty("hibUsername");
			hibPassword = prop.getProperty("hibPassword");
			hibDialect = prop.getProperty("hibDialect");
			hibUrl = prop.getProperty("hibUrl");
			
			confPath = prop.getProperty("confPath");
			driver = prop.getProperty("mySqlDriver");
			dialect = prop.getProperty("MySQLDialect");
			url = prop.getProperty("MySQLUrl");
		} catch (IOException e) {
			throw new IOException("File config.properties non trovato!");
		}
	}

	public String getConfPath(){
		return this.confPath;
	}
	
	public String getDriver(){
		return this.driver;
	}

	public String getDialect(){
		return this.dialect;
	}

	public String getUrl(){
		return this.url;
	}
	
	public String getHibDriver(){
		return this.hibDriver;
	}
	
	public String getHibUsername(){
		return this.hibUsername;
	}
	
	public String getHibPassword(){
		return this.hibPassword;
	}
	
	public String getHibUrl(){
		return this.hibUrl;
	}
	
	public String getHibDialect(){
		return this.hibDialect;
	}
}