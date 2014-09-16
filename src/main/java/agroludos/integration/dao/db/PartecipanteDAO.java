package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.PartecipanteTO;

public interface PartecipanteDAO extends UtenteDAO<PartecipanteTO>{

	PartecipanteTO readByCF(String cf) throws DatabaseException;

}