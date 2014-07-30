package agroludos.integration.dao.mysql;

import agroludos.integration.dao.FConfigurazioneDAO;
import agroludos.to.ConfigurazioneTO;
import agroludos.to.DatabaseTO;

public class MySqlConfigurazioneDAO implements FConfigurazioneDAO {

	@Override
	public boolean creaConfigurazione(DatabaseTO dbto) {
		return false;
	}

	@Override
	public boolean salvaConfigurazione(ConfigurazioneTO conf) {
		
		return false;
	}

}
