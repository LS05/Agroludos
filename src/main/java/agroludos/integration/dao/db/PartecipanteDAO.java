package agroludos.integration.dao.db;

import agroludos.to.PartecipanteTO;
import agroludos.to.UtenteTO;

public interface PartecipanteDAO {
	boolean crea(PartecipanteTO mdcto);
	PartecipanteTO read(UtenteTO uto);
	<T> PartecipanteTO readByUsername(T username);
	<T> PartecipanteTO readByID(T id);
}
