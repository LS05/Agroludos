package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.UtenteTO;

public interface UtenteDAO extends DAO<UtenteTO>{

	UtenteTO getUtente(UtenteTO uto) throws DatabaseException;

	boolean esisteUsername(UtenteTO uto) throws DatabaseException;

	boolean esisteEmail(UtenteTO uto) throws DatabaseException;

	UtenteTO getByUsername(String username) throws DatabaseException;

	UtenteTO getByID(Integer id) throws DatabaseException;

	void setNomeRuolo(UtenteTO uto) throws DatabaseException;

	void setNomeStatoUtente(UtenteTO uto) throws DatabaseException;

}