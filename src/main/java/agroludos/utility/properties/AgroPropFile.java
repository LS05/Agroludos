package agroludos.utility.properties;

import java.util.Properties;

public interface AgroPropFile {

	Properties getPropertyFile();

	String getProperty(String rule);

}
