package agroludos.presentation.resph;

import agroludos.presentation.resp.AgroResponse;

class AgroResponseContextImpl implements AgroResponseContext{
	private AgroResponse response;
	private String viewName;
	
	AgroResponseContextImpl(String viewName){
		this.viewName = viewName;
	}
	
	@Override
	public String getLogicalViewName(){
		return this.viewName;
	}

	@Override
	public void setResponse(AgroResponse response) {
		this.response = response;
	}

	@Override
	public AgroResponse getResponse() {
		return this.response;
	}
}