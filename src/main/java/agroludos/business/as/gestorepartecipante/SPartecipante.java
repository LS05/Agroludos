package agroludos.business.as.gestorepartecipante;

import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface SPartecipante {
	boolean inserisciPartecipante(PartecipanteTO parto) throws DatabaseException;
	boolean modificaPartecipante (PartecipanteTO parto) throws DatabaseException;
}
