package agroludos.presentation.resph;

import agroludos.presentation.resp.AgroResponse;

public interface AgroResponseContext {
	
	void setResponse(AgroResponse response);
	
	String getLogicalViewName();
	
	AgroResponse getResponse();
	
}
