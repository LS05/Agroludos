package agroludos.business.bd;

import agroludos.exceptions.ApplicationException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resph.AgroResponseContext;

public interface BusinessDelegate {

	AgroResponse gestisciServizio(Command command,
			AgroRequestContext request) throws ApplicationException;

}