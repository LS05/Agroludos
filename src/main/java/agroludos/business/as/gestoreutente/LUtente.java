package agroludos.business.as.gestoreutente;

import java.io.IOException;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.to.UtenteTO;

public interface LUtente extends AgroludosService {	
	public UtenteTO autenticazioneUtente(UtenteTO uto) throws DatabaseException, UserNotFoundException;

	UtenteTO getUtenteByUsername(UtenteTO uto) throws DatabaseException,
			UserNotFoundException, IOException;

	UtenteTO checkUtente(UtenteTO uTO) throws DatabaseException,
			UserNotFoundException, IOException;

	UtenteTO passwordDimenticata(UtenteTO uTO) throws DatabaseException,
			UserNotFoundException, IOException;
}
