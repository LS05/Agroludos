package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO {
	boolean crea(PartecipanteTO mdcto) throws DatabaseException;
	<T> PartecipanteTO readByUsername(T username);
	<T> PartecipanteTO readByID(T id);
	<T> PartecipanteTO readByCF(T cf);
	boolean update(PartecipanteTO parto) throws DatabaseException;
	boolean delete(PartecipanteTO parto) throws DatabaseException;
	List<PartecipanteTO> readAll();
}
