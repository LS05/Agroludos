package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.to.IscrizioneTO;

public interface SIscrizione extends AgroludosService {
	IscrizioneTO inserisciIscrizione(IscrizioneTO iscto) throws DatabaseException, ValidationException;
	IscrizioneTO modificaIscrizione(IscrizioneTO iscto) throws DatabaseException;
	IscrizioneTO eliminaIscrizione(IscrizioneTO iscto) throws DatabaseException;
}
