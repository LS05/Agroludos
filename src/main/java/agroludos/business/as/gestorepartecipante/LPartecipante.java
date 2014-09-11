package agroludos.business.as.gestorepartecipante;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface LPartecipante extends AgroludosService {
	PartecipanteTO getPartecipante(PartecipanteTO parto) throws DatabaseException;
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;
	PartecipanteTO getPartecipanteById(PartecipanteTO parto) throws DatabaseException;
}
