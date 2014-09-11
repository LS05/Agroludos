package agroludos.business.bd;

import agroludos.business.as.AgroludosService;
import agroludos.exceptions.ApplicationException;
import agroludos.exceptions.ServiceCacheException;
import agroludos.exceptions.ServiceHandlerException;
import agroludos.presentation.controller.mapper.Command;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;

class AgroludosBD implements BusinessDelegate{
	private ServiceLocator locator;
	private ServiceHandler handler;
	
	AgroludosBD(ServiceLocator locator, ServiceHandler handler){
		this.locator = locator;
		this.handler = handler;
	}
	
	@Override
	public AgroResponse gestisciServizio(Command command, AgroRequestContext request) throws ApplicationException{
		AgroludosService service = null;
		
		try {
			service = this.locator.lookup(command.getClassName());
			this.handler.setService(service);
			this.handler.handleService(command, request);
		} catch(ServiceHandlerException e){
			e.printStackTrace();
		} catch (ServiceCacheException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
