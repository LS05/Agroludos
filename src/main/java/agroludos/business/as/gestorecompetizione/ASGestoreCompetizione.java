package agroludos.business.as.gestorecompetizione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;

class ASGestoreCompetizione extends AgroludosAS implements LCompetizione, SCompetizione{

	@Override
	public boolean inserisciCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean annullaCompetizione(CompetizioneTO cmpto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
