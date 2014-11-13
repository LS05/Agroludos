package agroludos.utility.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class PropertiesFileImpl implements AgroPropFile{

	private Properties prop;

	PropertiesFileImpl() throws IOException{
		this.prop = new Properties();
	}

	@Override
	public Properties getPropertyFile(String pathName) throws IOException{
		StringBuilder pathBuilder = new StringBuilder(300);
		pathBuilder.append("/properties/");
		pathBuilder.append(pathName);

		Path propFile = Paths.get(pathBuilder.toString());
		String path = propFile.toString();
		InputStream inputStream = this.getClass().getResourceAsStream(path);

		if (inputStream == null) {
			throw new FileNotFoundException();
		}

		this.prop.load(inputStream);
		return this.prop;
	}

}