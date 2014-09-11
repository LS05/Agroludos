package agroludos.business.as.gestoretipocompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;


public interface STipoCompetizione extends AgroludosService{
	boolean inserisciTipoCompetizione(TipoCompetizioneTO tcmto) throws DatabaseException;
}
