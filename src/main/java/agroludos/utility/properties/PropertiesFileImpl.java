package agroludos.utility.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class PropertiesFileImpl implements AgroPropFile{

	private Properties prop;
	
	PropertiesFileImpl(String pathName) throws IOException{
		
		this.prop = new Properties();
		Path propFile = Paths.get(pathName);
		String path = propFile.toString();
		InputStream inputStream = this.getClass().getResourceAsStream(path);
		
		if (inputStream == null) {
			StringBuilder sb = new StringBuilder(100);
			sb.append("File non trovato nel percorso specificato: ");
			sb.append(path);
			throw new FileNotFoundException(sb.toString());
		}
		
		this.prop.load(inputStream);
	}
	
	@Override
	public Properties getPropertyFile(){
		return this.prop;
	}
	
	@Override
	public String getProperty(String rule){
		return this.prop.getProperty(rule);
	}
}
