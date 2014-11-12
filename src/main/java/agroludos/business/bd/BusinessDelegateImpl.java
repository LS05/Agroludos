package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.business.BusinessComponentNotFoundException;
import agroludos.exceptions.system.ApplicationException;
import agroludos.exceptions.system.ServiceHandlerException;
import agroludos.exceptions.system.ServiceNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

class BusinessDelegateImpl implements BusinessDelegate{
	private ServiceLocator locator;
	private ServiceHandler handler;

	BusinessDelegateImpl(ServiceLocator locator, ServiceHandler handler){
		this.locator = locator;
		this.handler = handler;
	}

	@Override
	public AgroResponseContext gestisciServizio(Command command, AgroRequestContext request) throws ApplicationException {
		AgroludosService applicationService = null;
		AgroResponseContext res = null;

		try {
			applicationService = this.locator.lookup(command.getClassName());
			this.handler.setService(applicationService);
			res = this.handler.handleService(command, request);
		} catch (BusinessComponentNotFoundException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		} catch (ServiceNotFoundException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		} catch(ServiceHandlerException e){
			// TODO Eccezione di programmazione
			// Sollevata solo in caso in cui ho una IllegalAccess o IllegalArgument
			// nell'handleService quindi da qui il programma deve chiudersi; 
			e.printStackTrace();
		} 

		return res;
	}
}
