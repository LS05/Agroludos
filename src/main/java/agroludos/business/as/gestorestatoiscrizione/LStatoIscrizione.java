package agroludos.business.as.gestorestatoiscrizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.StatoIscrizioneTO;

public interface LStatoIscrizione extends AgroludosService{
	List<StatoIscrizioneTO> getAllStatoIscrizione() throws DatabaseException;
	StatoIscrizioneTO getStatoAttivo() throws DatabaseException;
}
