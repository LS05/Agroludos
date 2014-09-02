package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.CompetizioneTO;

public interface CompetizioneDAO {

	boolean crea(CompetizioneTO cmpto);

	boolean update(CompetizioneTO cmpto);

	boolean annullaCompetizione(CompetizioneTO cmpto);

	List<CompetizioneTO> readAll();

	List<CompetizioneTO> readByTipo(CompetizioneTO cmpto);
	
	List<CompetizioneTO> readByMdc(CompetizioneTO cmpto);
}
