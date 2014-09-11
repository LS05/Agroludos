package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

public interface LTipoCompetizione extends AgroludosService{
	List<TipoCompetizioneTO> getAllTipoCompetizioneTOs() throws DatabaseException;
}
