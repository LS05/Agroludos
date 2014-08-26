package agroludos.integration.dao.db;

import agroludos.to.UtenteTO;

public interface UtenteDAO {
	UtenteTO read(UtenteTO uto);
	UtenteTO readByUsername(String username);
	UtenteTO readByID(String id);
}
