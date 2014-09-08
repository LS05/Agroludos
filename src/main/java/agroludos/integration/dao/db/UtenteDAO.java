package agroludos.integration.dao.db;

import agroludos.to.UtenteTO;

public interface UtenteDAO {
	UtenteTO autenticazione(UtenteTO uto);
	<T> boolean checkUsername(T username);
	<T> boolean checkEmail(T email);
	<T> UtenteTO  readByUsername(T username);
	<T> UtenteTO readByID(T id);
}
