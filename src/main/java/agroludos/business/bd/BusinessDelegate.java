package agroludos.business.bd;

import agroludos.exceptions.system.ApplicationException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * <b>Business Tier</b></br>
 * L'interfaccia rappresenta il pattern <strong>Business Delegate Controller</strong>
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
public interface BusinessDelegate {

	AgroResponseContext gestisciServizio(Command command,
			AgroRequestContext request) throws ApplicationException;

}