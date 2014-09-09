package agroludos.integration.dao.db;

import java.util.List;

import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO {
	boolean crea(PartecipanteTO mdcto);
	<T> PartecipanteTO readByUsername(T username);
	<T> PartecipanteTO readByID(T id);
	boolean update(PartecipanteTO parto);
	boolean delete(PartecipanteTO parto);
	List<PartecipanteTO> readAll();
}
