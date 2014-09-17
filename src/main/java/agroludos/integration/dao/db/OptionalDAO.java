package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.DatabaseException;
import agroludos.to.OptionalTO;

public interface OptionalDAO extends DAO<OptionalTO>{

	public OptionalTO eliminaOptional(OptionalTO optTO) throws DatabaseException;

	public List<OptionalTO> readByTipo(OptionalTO optTO) throws DatabaseException;

	public void setNomeStatoOpt(OptionalTO optTO) throws DatabaseException;

	public void setNomeTipoOpt(OptionalTO optTO) throws DatabaseException;
}
