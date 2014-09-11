package agroludos.business.as.gestorepartecipante;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface SPartecipante extends AgroludosService{
	boolean inserisciPartecipante(PartecipanteTO parto) throws DatabaseException;
	boolean modificaPartecipante (PartecipanteTO parto) throws DatabaseException;
}
