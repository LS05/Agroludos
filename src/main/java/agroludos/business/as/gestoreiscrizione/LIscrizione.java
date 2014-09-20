package agroludos.business.as.gestoreiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;


public interface LIscrizione extends AgroludosService {
	List<IscrizioneTO> getAllIscrizione() throws DatabaseException;

}
