package agroludos.business.as.gestorepartecipante;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface LPartecipante extends AgroludosService {
	<T> PartecipanteTO getPartecipante(T username) throws DatabaseException;
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;
	<T> PartecipanteTO getPartecipanteById(T id) throws DatabaseException;
}
