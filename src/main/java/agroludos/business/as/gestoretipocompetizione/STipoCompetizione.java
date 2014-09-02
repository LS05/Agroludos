package agroludos.business.as.gestoretipocompetizione;

import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;


public interface STipoCompetizione {
	boolean inserisciTipoCompetizione(TipoCompetizioneTO tcmto) throws DatabaseException;
}
