package agroludos.integration.dao.db;

import agroludos.exceptions.DatabaseException;
import agroludos.to.UtenteTO;

public interface UtenteDAO<T extends UtenteTO> extends DAO<T>{

	T deleteUtente(T uto) throws DatabaseException;
	
	T getUtente(UtenteTO uto) throws DatabaseException;

	boolean esisteUsername(UtenteTO uto) throws DatabaseException;

	boolean esisteEmail(UtenteTO uto) throws DatabaseException;

	T getByUsername(String username) throws DatabaseException;

	T getByID(Integer id) throws DatabaseException;

}