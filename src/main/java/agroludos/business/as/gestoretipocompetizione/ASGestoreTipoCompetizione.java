package agroludos.business.as.gestoretipocompetizione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.TipoCompetizioneTO;

class ASGestoreTipoCompetizione extends AgroludosAS implements LTipoCompetizione, STipoCompetizione{

	@Override
	public boolean inserisciTipoCompetizione(TipoCompetizioneTO mdcto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}


}