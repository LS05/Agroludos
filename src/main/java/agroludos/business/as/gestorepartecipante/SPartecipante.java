package agroludos.business.as.gestorepartecipante;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.ValidationException;
import agroludos.to.PartecipanteTO;

public interface SPartecipante extends AgroludosService{
	boolean inserisciPartecipante(PartecipanteTO parto) throws DatabaseException, ValidationException;
	PartecipanteTO modificaPartecipante (PartecipanteTO parto) throws DatabaseException, ValidationException;
}
