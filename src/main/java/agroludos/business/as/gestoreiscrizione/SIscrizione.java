package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.IscrizioneTO;

public interface SIscrizione extends AgroludosService {
	IscrizioneTO inserisciIscrizione(IscrizioneTO iscto) throws DatabaseException, ValidationException;
	IscrizioneTO modificaIscrizioneByPartecipante(IscrizioneTO iscto) throws DatabaseException;
	IscrizioneTO eliminaIscrizioneByPartecipante(IscrizioneTO iscto) throws DatabaseException;
	IscrizioneTO eliminaIscrizioneByMdc(IscrizioneTO iscTO) throws DatabaseException;
	IscrizioneTO modificaIscrizioneByMdc(IscrizioneTO iscTO)
			throws DatabaseException;
}
