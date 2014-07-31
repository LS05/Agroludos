package agroludos.integration.dao;

import agroludos.to.DatabaseTO;

public interface FConfigurazioneDAO {
	boolean creaConfigurazione(DatabaseTO dbto);
	String getConfPath();
}
