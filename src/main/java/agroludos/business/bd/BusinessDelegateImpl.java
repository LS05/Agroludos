package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ApplicationException;
import agroludos.exceptions.ServiceHandlerException;
import agroludos.exceptions.BusinessComponentNotFoundException;
import agroludos.exceptions.ServiceNotFoundException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resph.AgroResponseContext;

/**
 * <b>Business Tier</b></br>
 * La classe modella e implementa un <b>Business Delegate</b>.
 * Il business Delegate rappresenta un'astrazione client-side dei servizi di business: 
 * ovvero astrae e nascone i dettagli implementativi dei servizi di business services. 
 * Utilizzando il Business Delegate è possibile ridurre l'accoppiamento tra
 * i client i servizi di business offerti dal sistema.
 * 
 * TODO Discutere dell'implementazione 
 * Depending on the implementation strategy, the Business Delegate might shield clients from 
 * possible volatility in the implementation of the business service API. 
 * Potentially, this reduces the number of changes that must be made to the client code 
 * when the business service API or its underlying implementation changes.
 * 
 * L'obiettivo della classe è quello di incapsulare l'accesso a un servizio di business.
 * Il business delegate nasconde i dettagli di implementazione di un servizio di business 
 * come i meccanismi di lookup (del suo ritrovamento) e di accesso.
 * 
 * @author Luca Suriano
 * @author Francesco Zagaria
 *
 */
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
