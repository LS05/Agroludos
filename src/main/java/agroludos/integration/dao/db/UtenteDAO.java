package agroludos.integration.dao.db;

import agroludos.to.UtenteTO;

public interface UtenteDAO {
	boolean crea(UtenteTO uto);
	UtenteTO autenticazione(UtenteTO uto);
	<T> boolean checkUsername(T username);
	<T> boolean checkEmail(T email);
}
