package agroludos.presentation.controller.mapper;

import agroludos.business.bd.BusinessDelegate;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

class CommandProcessorImpl implements CommandProcessor{
	private BusinessDelegate businessDelegate;

	CommandProcessorImpl(BusinessDelegate businessDelegate){
		this.businessDelegate = businessDelegate;
	}

	@Override
	public AgroResponseContext invoke(Command command, AgroRequestContext request) throws ApplicationException {
		return this.businessDelegate.gestisciServizio(command, request);
	}
}