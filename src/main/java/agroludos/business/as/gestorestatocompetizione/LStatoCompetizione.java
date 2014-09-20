package agroludos.business.as.gestorestatocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.StatoCompetizioneTO;

public interface LStatoCompetizione extends AgroludosService{
	List<StatoCompetizioneTO> getAllStatoCompetizioneTO() throws DatabaseException;
	StatoCompetizioneTO getStatoCmpAnnullata() throws DatabaseException;
}
