package agroludos.utility.properties;

import java.io.IOException;
import java.util.Properties;

public interface AgroPropFile {

	Properties getPropertyFile(String pathName) throws IOException;

}
