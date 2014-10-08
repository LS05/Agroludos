package agroludos.integration.dao.db;


import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;

public interface OptionalDAO extends DAO<OptionalTO>{

	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException;

}
