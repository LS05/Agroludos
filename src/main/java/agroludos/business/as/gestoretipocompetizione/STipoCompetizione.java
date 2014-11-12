package agroludos.business.as.gestoretipocompetizione;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.TipoCompetizioneTO;


public interface STipoCompetizione extends AgroludosService{
	TipoCompetizioneTO inserisciTipoCompetizione(TipoCompetizioneTO tcmto) throws DatabaseException, ValidationException;
}
