package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

public interface LTipoCompetizione extends AgroludosService{
	List<TipoCompetizioneTO> getAllTipoCompetizione() throws DatabaseException;
}
