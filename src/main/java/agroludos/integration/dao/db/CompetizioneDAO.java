package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.CompetizioneTO;

public interface CompetizioneDAO {

	boolean crea(CompetizioneTO cmpto);

	boolean update(CompetizioneTO cmpto);

	boolean annullaCompetizione(CompetizioneTO cmpto);

	List<CompetizioneTO> readAll();

	<T>List<CompetizioneTO> readByTipo(T tipo);
	
	<T>List<CompetizioneTO> readByMdc(T mdc);

	<T>CompetizioneTO readById(T id);
	
	List<CompetizioneTO> readCompetizioniAttive();
}
