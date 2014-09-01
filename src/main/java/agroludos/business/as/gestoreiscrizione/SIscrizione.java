package agroludos.business.as.gestoreiscrizione;

import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;
import agroludos.to.OptionalTO;

public interface SIscrizione {
	boolean inserisciIscrizione(IscrizioneTO iscto) throws DatabaseException;
	boolean modificaIscrizione(IscrizioneTO iscto) throws DatabaseException;
	boolean eliminaIscrizione(IscrizioneTO iscto) throws DatabaseException;
}
