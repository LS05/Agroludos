package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.TipoCompetizioneTO;

public interface TipoCompetizioneDAO {

	boolean crea(TipoCompetizioneTO tcpto);
	
	List<TipoCompetizioneTO> readAll();
}
