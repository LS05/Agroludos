package agroludos.integration.dao.db;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO extends UtenteDAO<PartecipanteTO>{

	PartecipanteTO readByCF(String cf) throws DatabaseException;

	PartecipanteTO readByUsername(String username) throws DatabaseException;

}