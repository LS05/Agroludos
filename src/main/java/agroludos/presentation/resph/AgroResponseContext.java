package agroludos.presentation.resph;

import agroludos.presentation.resp.AgroResponse;

public interface AgroResponseContext {
	
	void setData(Object data);
	
	Object getData();
	
	void setResponse(AgroResponse response);
	
	String getLogicalViewName();
	
	AgroResponse getResponse();

	void setLogicalViewName(String viewName);
	
}
