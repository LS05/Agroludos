package agroludos.business.as.gestorepartecipante;

import java.io.IOException;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.ValidationException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface SPartecipante extends AgroludosService{

	PartecipanteTO inserisciPartecipante(PartecipanteTO parto) throws DatabaseException, ValidationException, IOException;

	PartecipanteTO modificaPartecipante (PartecipanteTO parto) throws DatabaseException, ValidationException, IOException;

}
