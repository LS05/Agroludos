package agroludos.presentation.controller.mapper;

import agroludos.business.bd.BusinessDelegate;
import agroludos.exceptions.ApplicationException;
import agroludos.presentation.reqh.AgroRequestContext;
import agroludos.presentation.resp.AgroResponse;
import agroludos.presentation.resp.ResponseFactory;
import agroludos.presentation.resph.AgroResponseContext;
import agroludos.presentation.resph.ResposeContextFactory;

class CommandProcessorImpl implements CommandProcessor{
	private ResposeContextFactory respFact;
	private AgroResponse response;
	private BusinessDelegate businessDelegate;

	CommandProcessorImpl(){
		ResponseFactory respFact = new ResponseFactory();
		this.response = respFact.createResponse();
	}

	@Override
	public AgroResponseContext invoke(Command command, AgroRequestContext request) {
		AgroResponseContext responseContext = null;
		try {
			this.response = this.businessDelegate.gestisciServizio(command, request);
			responseContext = this.respFact.createResponseContext(this.response);
		} catch (ApplicationException e) {
			this.response.setViewPath(command.getFailPath());
			responseContext = this.respFact.createResponseContext(this.response);
		}
		
		return responseContext;
	}

	public void setRespFact(ResposeContextFactory respFact) {
		this.respFact = respFact;
	}

	public void setBusinessDelegate(BusinessDelegate businessDelegate) {
		this.businessDelegate = businessDelegate;
	}
}