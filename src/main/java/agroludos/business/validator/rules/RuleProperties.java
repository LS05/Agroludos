package agroludos.business.validator.rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class RuleProperties {
	Properties prop;
	
	RuleProperties() throws IOException{
		
		this.prop = new Properties();
		
		Path propFile = Paths.get("agroludos/business/validator/rules/rules.properties");
		String path = propFile.toString();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
		
		if (inputStream == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("File non trovato nel percorso specificato: ");
			sb.append(path);
			throw new FileNotFoundException(sb.toString());
		}
		
		this.prop.load(inputStream);
	}
	
	String getProperty(String rule){
		return this.prop.getProperty(rule);
	}
}
