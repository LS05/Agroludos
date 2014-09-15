package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.exceptions.UserNotFoundException;
import agroludos.to.UtenteTO;

public interface UtenteDAO {

	boolean crea(UtenteTO uto) throws DatabaseException;

	UtenteTO autenticazione(UtenteTO uto) throws UserNotFoundException;

	boolean esisteUsername(UtenteTO uto) throws DatabaseException;

	boolean esisteEmail(UtenteTO uto) throws DatabaseException;

	UtenteTO getByUsername(String username) throws DatabaseException;

	UtenteTO getByID(Integer id) throws DatabaseException;

	void setNomeRuolo(UtenteTO uto) throws DatabaseException;

	void setNomeStatoUtente(UtenteTO uto) throws DatabaseException;

}