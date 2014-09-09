package agroludos.business.as.gestorepartecipante;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface LPartecipante {
	<T> PartecipanteTO getPartecipante(T username) throws DatabaseException;
	List<PartecipanteTO> getAllPartecipante() throws DatabaseException;
	<T> PartecipanteTO getPartecipanteById(T id) throws DatabaseException;
}
