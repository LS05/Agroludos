package agroludos.business.as.gestoreiscrizione;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.IscrizioneTO;

class ASGestoreIscrizione extends AgroludosAS implements LIscrizione, SIscrizione{

	@Override
	public boolean inserisciIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminaIscrizione(IscrizioneTO iscto)
			throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

}
