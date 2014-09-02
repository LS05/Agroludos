package agroludos.business.as.gestoretipocompetizione;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

public interface LTipoCompetizione {
	List<TipoCompetizioneTO> getAllTipoCompetizioneTOs() throws DatabaseException;
}
