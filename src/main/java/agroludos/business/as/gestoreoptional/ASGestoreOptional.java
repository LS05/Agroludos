package agroludos.business.as.gestoreoptional;

import agroludos.business.as.AgroludosAS;
import agroludos.exceptions.DatabaseException;
import agroludos.to.CompetizioneTO;
import agroludos.to.OptionalTO;

class ASGestoreOptional extends AgroludosAS implements LOptional, SOptional{

	@Override
	public boolean inserisciOptional(OptionalTO optto) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificaOptional(OptionalTO optto) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminaOptional(OptionalTO optto) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	
}
