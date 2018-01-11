package agroludos.integration.dao.db;

import java.util.List;

import agroludos.exceptions.system.DatabaseException;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

/** 
 * Data Access Object per tutte le operazioni CRUD per quanto riguarda gli optional.
 * Sono presenti i metodi di lettura e modifica applicabili.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface OptionalDAO extends DAO<OptionalTO>{

	/**
	 * Modifa un optional cambiando il suo stato in disattivo
	 * @param optTO
	 * @return
	 * @throws DatabaseException
	 */
	public OptionalTO disattivaOptional(OptionalTO optTO) throws DatabaseException;

	/**
	 * Restituisce la lista degli optional attivi del tipo in input
	 * @param toptTO
	 * @return
	 * @throws DatabaseException
	 */
	public List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException;

	/**
	 * Restituisce la lista di tutti gli optional del tipo in input
	 * @param toptTO
	 * @return
	 * @throws DatabaseException
	 */	
	public List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException;

}
