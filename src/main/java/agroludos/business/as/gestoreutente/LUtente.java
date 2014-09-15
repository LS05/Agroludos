package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.to.UtenteTO;

public interface LUtente extends AgroludosService {	
	public UtenteTO autencazioneUtente(UtenteTO uto) throws DatabaseException, UserNotFoundException;
}
