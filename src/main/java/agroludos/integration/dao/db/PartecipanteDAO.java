package agroludos.integration.dao.db;

import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO {
	boolean crea(PartecipanteTO mdcto);
	<T> PartecipanteTO readByUsername(T username);
	<T> PartecipanteTO readByID(T id);
	boolean update(PartecipanteTO parto);
}
