package agroludos.business.as.gestorecompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

public interface SCompetizione extends AgroludosService {
	boolean inserisciCompetizione(CompetizioneTO cmpto) throws DatabaseException;
	boolean modificaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
	boolean annullaCompetizione(CompetizioneTO cmpto) throws DatabaseException;
}
