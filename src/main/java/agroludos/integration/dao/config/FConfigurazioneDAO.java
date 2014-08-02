package agroludos.integration.dao.config;

import agroludos.to.DatabaseTO;

public interface FConfigurazioneDAO {
	boolean creaConfigurazione(DatabaseTO dbto);
	String getConfPath();
}
