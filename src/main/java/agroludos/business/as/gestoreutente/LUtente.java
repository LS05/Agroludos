package agroludos.business.as.gestoreutente;

import agroludos.exceptions.DatabaseException;
import agroludos.to.UtenteTO;

public interface LUtente {	
	public UtenteTO getUtente(UtenteTO uto) throws DatabaseException;
}
