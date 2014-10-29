package agroludos.business.validator.rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class RuleProperties {
	private Properties prop;

	RuleProperties() throws IOException{
		this.prop = new Properties();
		Path propFile = Paths.get("/properties/validator/rules.properties");
		String path = propFile.toString();
		InputStream inputStream = this.getClass().getResourceAsStream(path);

		if (inputStream == null) {
			throw new FileNotFoundException();
		}

		this.prop.load(inputStream);
	}

	String getProperty(String rule){
		return this.prop.getProperty(rule);
	}
}