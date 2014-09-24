package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;

public interface SIscrizione extends AgroludosService {
	boolean inserisciIscrizione(IscrizioneTO iscto) throws DatabaseException;
	IscrizioneTO modificaIscrizione(IscrizioneTO iscto) throws DatabaseException;
	IscrizioneTO eliminaIscrizione(IscrizioneTO iscto) throws DatabaseException;
}
