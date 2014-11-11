package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UtenteEsistenteException;
import agroludos.exceptions.ValidationException;
import agroludos.to.UtenteTO;
public interface SUtente extends AgroludosService {
	
	UtenteTO modificaDatiAccesso(UtenteTO uTO) throws UtenteEsistenteException, 
	DatabaseException, ValidationException;
}
