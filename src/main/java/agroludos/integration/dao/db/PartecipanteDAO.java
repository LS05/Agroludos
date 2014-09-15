package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO {

	boolean crea(PartecipanteTO mdcto) throws DatabaseException;

	PartecipanteTO getByUsername(String username) throws DatabaseException;

	PartecipanteTO readByID(Integer id) throws DatabaseException;

	PartecipanteTO readByCF(String cf) throws DatabaseException;

	boolean update(PartecipanteTO parto) throws DatabaseException;

	boolean delete(PartecipanteTO parto) throws DatabaseException;

	List<PartecipanteTO> readAll() throws DatabaseException;

}