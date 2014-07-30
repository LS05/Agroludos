package agroludos.integration.dao;

import agroludos.to.ConfigurazioneTO;

public interface FConfigurazioneDAO extends ConfigurazioneDAO {
	boolean salvaConfigurazione(ConfigurazioneTO conf);
}
