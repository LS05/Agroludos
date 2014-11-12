package agroludos.business.as.gestoreutente;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.business.UtenteEsistenteException;
import agroludos.exceptions.business.ValidationException;
import agroludos.to.UtenteTO;
public interface SUtente extends AgroludosService {
	
	UtenteTO modificaDatiAccesso(UtenteTO uTO) throws UtenteEsistenteException, 
	DatabaseException, ValidationException;
}
