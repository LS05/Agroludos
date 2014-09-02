package agroludos.presentation.resph;

import agroludos.presentation.resp.AgroResponse;

public class ResposeContextFactory{
	private AgroResponseContext responseContext;
	
	public AgroResponseContext createResponseContext(AgroResponse response) {
		this.responseContext = new AgroResponseContextImpl(response.getViewName());
		this.responseContext.setResponse(response);
		return this.responseContext;
	}
}
