package agroludos.business.as.gestoreoptional;

import java.util.List;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.OptionalCmpAttiveException;
import agroludos.exceptions.system.DatabaseException;
import agroludos.to.OptionalTO;
import agroludos.to.TipoOptionalTO;

/**
 * L'interfaccia rappresenta i servizi di lettura applicabili ad un Optional
 * Estende l'interfaccia AgroludosService
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface LOptional extends AgroludosService{
	
	/**
	 * 
	 * @param toptTO
	 * @return List di {@link OptionalTO}
	 * @throws DatabaseException
	 */
	List<OptionalTO> getOptionalByTipo(TipoOptionalTO toptTO) throws DatabaseException;
	
	/**
	 * 
	 * @param toptTO
	 * @return List di {@link OptionalTO}
	 * @throws DatabaseException
	 */
	List<OptionalTO> getAllOptional() throws DatabaseException;

	/**
	 * 
	 * @param optto
	 * @return {@link OptionalTO}
	 * @throws DatabaseException
	 * @throws OptionalCmpAttiveException
	 */
	OptionalTO checkOptCmpAttive(OptionalTO optto) throws DatabaseException,
			OptionalCmpAttiveException;

	/**
	 * 
	 * @param toptTO
	 * @return List di {@link OptionalTO}
	 * @throws DatabaseException
	 */
	List<OptionalTO> getOptionalAttiviByTipo(TipoOptionalTO toptTO) throws DatabaseException;
	
}